package com.sobey.cmop.mvc.service.basicdata;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.dao.HostServerDao;
import com.sobey.cmop.mvc.dao.NicDao;
import com.sobey.cmop.mvc.dao.custom.HostServerDaoCustom;
import com.sobey.cmop.mvc.dao.custom.IpPoolDaoCustom;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.HostServer;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.Nic;
import com.sobey.cmop.mvc.entity.ServerModel;
import com.sobey.cmop.mvc.service.vm.HostTree;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.Identities;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.cmop.mvc.Interface.IpPoolDaoCustom;
import com.sobey.cmop.mvc.DTO.IpPool;
import com.sobey.cmop.mvc.DTO.SearchFilter;
import com.sobey.cmop.mvc.DTO.ComputeItem;
@Service
@Transactional(readOnly = true)
public class HostServerService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  HostServerDao hostServerDao;

@Resource
 private  HostServerDaoCustom hostServerDaoCustom;

@Resource
 private  NicDao nicDao;

@Resource
 private  IpPoolDaoCustom ipPoolDaoCustom;

 private  String SWITCH_FORMAT;


public HostServer getHostServer(Integer id){
    return hostServerDao.findOne(id);
}


@Transactional(readOnly = false)
public void deleteNic(Collection<Nic> nics){
    nicDao.delete(nics);
}


@SuppressWarnings("rawtypes")
public List<ComputeItem> getEcsByHost(Integer id){
    List<ComputeItem> computeItems = new ArrayList<ComputeItem>();
    List list = hostServerDaoCustom.getEcsByHost(id);
    for (int i = 0; i < list.size(); i++) {
        Object[] object = (Object[]) list.get(i);
        computeItems.add(comm.basicUnitService.wrapComputeItem(object));
    }
    return computeItems;
}


@Transactional(readOnly = false)
public String syn(){
    try {
        // 1. 同步所有宿主机及其虚拟机
        List list = HostTree.call();
        // 2. 更新宿主机对应IP状态及其关联虚拟机IP状态为：未使用
        int updateCount = ipPoolDaoCustom.updateIpPoolByStatus(IpPoolConstant.IpStatus.未使用.toInteger());
        // 3. 删除所有宿主机
        // int deleteCount = hostServerDaoCustom.deleteHostByServerType(1);
        List<String> hostList;
        List<Map> hostListMap;
        int hostCount = 0;
        int vmCount = 0;
        String ipAddress;
        List hostServerList;
        HostServer hostServer;
        for (int k = 0; k < list.size(); k++) {
            hostList = (List<String>) ((List) list.get(k)).get(0);
            hostListMap = (List<Map>) ((List) list.get(k)).get(1);
            for (int i = 0; i < hostList.size(); i++) {
                ipAddress = hostList.get(i);
                hostCount++;
                // 4. 写入新的宿主机
                hostServerList = hostServerDao.findByIpAddress(ipAddress);
                if (hostServerList != null && hostServerList.size() > 0) {
                    logger.info("已存在的宿主机：" + ipAddress + "，hostServerList.size=" + hostServerList.size());
                    hostServer = (HostServer) hostServerList.get(0);
                } else {
                    hostServer = new HostServer(1, IpPoolConstant.PoolType.私网IP池.toInteger(), hostList.get(i), // 名称默认为IP；IP池默认为私网IP池
                    new Date());
                    hostServer.setAlias(Identities.uuid2());
                    hostServer.setIpAddress(ipAddress);
                    // IDC别名默认西安IDC
                    hostServer.setLocationAlias(comm.locationService.getLocation(2).getAlias());
                    hostServerDao.save(hostServer);
                }
                // 5. 更新宿主机对应IP状态为：已使用
                if (comm.ipPoolService.findIpPoolByIpAddress(ipAddress) != null) {
                    logger.info("已存在的宿主机IP：" + ipAddress);
                    comm.ipPoolService.updateIpPoolByIpAddress(ipAddress, IpPoolConstant.IpStatus.已使用.toInteger(), null);
                } else {
                    comm.ipPoolService.saveIpPool(ipAddress, IpPoolConstant.PoolType.私网IP池.toInteger(), IpPoolConstant.IpStatus.已使用.toInteger(), comm.vlanService.getVlan(1), // 默认西安虚拟机内网VLAN
                    null);
                }
                // 6. 更新其关联虚拟机及其IP状态为：已使用
                Map host = (Map) hostListMap.get(i);
                List ecsList = (List) host.get(hostList.get(i));
                for (int j = 0; j < ecsList.size(); j++) {
                    ipAddress = (String) ecsList.get(j);
                    vmCount++;
                    if (comm.ipPoolService.findIpPoolByIpAddress(ipAddress) != null) {
                        logger.info("已存在的虚拟机IP：" + ipAddress);
                        comm.ipPoolService.updateIpPoolByIpAddress(ipAddress, IpPoolConstant.IpStatus.已使用.toInteger(), hostServer);
                    } else {
                        comm.ipPoolService.saveIpPool(ipAddress, IpPoolConstant.PoolType.私网IP池.toInteger(), IpPoolConstant.IpStatus.已使用.toInteger(), comm.vlanService.getVlan(1), // 默认西安虚拟机内网VLAN
                        hostServer);
                    }
                }
            }
        }
        return "true-" + hostCount + "-" + vmCount;
    } catch (Exception e) {
        e.printStackTrace();
        return "false-0-0";
    }
}


@Transactional(readOnly = false)
public void saveNic(HostServer hostServer,String[] nicSites,String[] nicMacs,String[] nicIpAddress){
    List<Nic> nics = this.getNicByhostServerId(hostServer.getId());
    if (!nics.isEmpty()) {
        // 删除oneCMDB中网卡数据.
        comm.oneCmdbUtilService.deleteServerPortToOneCMDB(hostServer);
        for (Nic nic : nics) {
            // 初始化修改前的IP.
            comm.ipPoolService.initIpPool(nic.getIpAddress());
        }
        this.deleteNic(nics);
    }
    if (nicSites != null) {
        int count = nicSites.length;
        for (int i = 0; i < count; i++) {
            // 更改IP状态为 已使用
            IpPool ipPool = comm.ipPoolService.findIpPoolByIpAddress(nicIpAddress[i]);
            ipPool.setStatus(IpPoolConstant.IpStatus.已使用.toInteger());
            comm.ipPoolService.saveOrUpdate(ipPool);
            String alias = "Nic" + Identities.uuid2();
            Nic nic = new Nic(hostServer, nicMacs[i], nicIpAddress[i], alias, nicSites[i]);
            this.saveOrUpdate(nic);
        }
    }
}


@Transactional(readOnly = false)
public boolean updateHostServer(Integer id,Integer serverType,Integer serverModelId,String rack,String site,String switchs,String switchSite,String height,String locationAlias,String ipAddress,String description,String managementMac,String[] nicSites,String[] nicMacs,String[] nicIpAddress){
    HostServer hostServer = this.getHostServer(id);
    return this.saveOrUpdateHostServer(hostServer, serverType, serverModelId, rack, site, switchs, switchSite, height, locationAlias, ipAddress, description, managementMac, nicSites, nicMacs, nicIpAddress);
}


@Transactional(readOnly = false)
public boolean delete(Integer id){
    HostServer hostServer = this.getHostServer(id);
    // 初始化IP.
    comm.ipPoolService.initIpPool(hostServer.getIpAddress());
    List<Nic> nics = this.getNicByhostServerId(id);
    for (Nic nic : nics) {
        comm.ipPoolService.initIpPool(nic.getIpAddress());
    }
    // 删除oneCMDB中的数据
    comm.oneCmdbUtilService.deleteHostServerToOneCMDB(hostServer);
    hostServerDao.delete(id);
    return true;
}


public Page<HostServer> getHostServerPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<HostServer> spec = DynamicSpecifications.bySearchFilter(filters.values(), HostServer.class);
    return hostServerDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public boolean saveHostServer(HostServer hostServer){
    if (hostServer.getId() != null) {
        // 更新新旧IP状态
        HostServer oldHostServer = getHostServer(hostServer.getId());
        if (!oldHostServer.getIpAddress().equals(hostServer.getIpAddress())) {
            // 更新旧IP状态：未使用
            comm.ipPoolService.updateIpPoolByIpAddress(oldHostServer.getIpAddress(), IpPoolConstant.IpStatus.未使用.toInteger(), null);
            // 判断新IP是否存在
            if (comm.ipPoolService.findIpPoolByIpAddress(hostServer.getIpAddress()) != null) {
                // 存在则更新IP状态：已使用
                comm.ipPoolService.updateIpPoolByIpAddress(hostServer.getIpAddress(), IpPoolConstant.IpStatus.已使用.toInteger(), null);
            } else {
                // 创建新的IP
                comm.ipPoolService.saveIpPool(hostServer.getIpAddress(), IpPoolConstant.PoolType.私网IP池.toInteger(), IpPoolConstant.IpStatus.已使用.toInteger(), comm.vlanService.getVlan(1), // 默认西安虚拟机内网VLAN
                null);
            }
        }
    } else {
        // 创建新的IP
        comm.ipPoolService.saveIpPool(hostServer.getIpAddress(), IpPoolConstant.PoolType.私网IP池.toInteger(), IpPoolConstant.IpStatus.已使用.toInteger(), comm.vlanService.getVlan(1), // 默认西安虚拟机内网VLAN
        null);
    }
    // 保存服务器
    hostServerDao.save(hostServer);
    return true;
}


public List<Nic> getNicByhostServerId(Integer hostServerId){
    return nicDao.findByHostServerId(hostServerId);
}


@Transactional(readOnly = false)
public boolean saveOrUpdateHostServer(HostServer hostServer,Integer serverType,Integer serverModelId,String rack,String site,String switchs,String switchSite,String height,String locationAlias,String ipAddress,String description,String managementMac,String[] nicSites,String[] nicMacs,String[] nicIpAddress){
    boolean flag = false;
    ServerModel serverModel = comm.serverModelService.getServerModel(serverModelId);
    String[] racks = StringUtils.split(rack, "&");
    String rackAlias = racks[0];
    String rackName = racks[1];
    /*
		 * DisplayName
		 * 
		 * Company Model Rack-Site
		 * 
		 * eg:HP DL2000 0416-1-1
		 */
    String displayName = serverModel.getCompany() + " " + serverModel.getName() + " " + rackName + "-" + site;
    String[] switchArray = StringUtils.split(switchs, "&");
    String switchAlias = switchArray[0];
    /*
		 * 交换机插入oneCMDB中的格式化模板.最终结果为 XA041101SW 1/0/12
		 * 
		 * XA041101SW:交换机名
		 * 
		 * 1/0/: 模板(常量)
		 * 
		 * 12:交换机位置
		 */
    String switchName = switchArray[1] + SWITCH_FORMAT + switchSite;
    // 判断服务器是否重名.
    if (this.findByDisplayName(displayName) == null || displayName.equals(hostServer.getDisplayName())) {
        // 初始化修改前的IP.
        comm.ipPoolService.initIpPool(hostServer.getIpAddress());
        IpPool ipPool = comm.ipPoolService.findIpPoolByIpAddress(ipAddress);
        hostServer.setDisplayName(displayName);
        hostServer.setIpAddress(ipAddress);
        hostServer.setLocationAlias(locationAlias);
        hostServer.setPoolType(ipPool.getPoolType());
        hostServer.setServerType(serverType);
        hostServer.setHeight(height);
        hostServer.setSite(site);
        hostServer.setServerModel(serverModel);
        hostServer.setDescription(description);
        hostServer.setRack(rackName);
        hostServer.setRackAlias(rackAlias);
        hostServer.setSwitchAlias(switchAlias);
        hostServer.setSwitchName(switchName);
        hostServer.setSwitchSite(switchSite);
        hostServer.setManagementMac(managementMac);
        // step.2 更改IP状态为 已使用
        ipPool.setStatus(IpPoolConstant.IpStatus.已使用.toInteger());
        comm.ipPoolService.saveOrUpdate(ipPool);
        this.saveOrUpdate(hostServer);
        // 保存网卡信息
        this.saveNic(hostServer, nicSites, nicMacs, nicIpAddress);
        // step.3 同步oneCMDB
        comm.oneCmdbUtilService.saveHostServerToOneCMDB(hostServer);
        flag = true;
    }
    return flag;
}


@Transactional(readOnly = false)
public void updateHostServerTree(String[] computeIds,String[] serverAlias){
    if (computeIds != null) {
        for (int i = 0; i < serverAlias.length; i++) {
            // step.1
            ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
            if (ComputeConstant.ComputeType.PCS.toInteger().equals(computeItem.getComputeType())) {
                computeItem.setServerAlias(serverAlias[i]);
            } else {
                computeItem.setHostServerAlias(serverAlias[i]);
            }
            comm.computeService.saveOrUpdate(computeItem);
            // step.2
            IpPool ipPool = comm.ipPoolService.findIpPoolByIpAddress(computeItem.getInnerIp());
            ipPool.setHostServer(this.findByAlias(serverAlias[i]));
            comm.ipPoolService.saveOrUpdate(ipPool);
        }
    }
}


public List<HostServer> findByServerType(int serverType){
    return hostServerDao.findByServerType(serverType);
}


@Transactional(readOnly = false)
public boolean addHostServer(Integer serverType,Integer serverModelId,String rack,String site,String switchs,String switchSite,String height,String locationAlias,String ipAddress,String description,String managementMac,String[] nicSites,String[] nicMacs,String[] nicIpAddress){
    String alias = "Host" + Identities.uuid2();
    HostServer hostServer = new HostServer();
    hostServer.setAlias(alias);
    hostServer.setCreateTime(new Date());
    return this.saveOrUpdateHostServer(hostServer, serverType, serverModelId, rack, site, switchs, switchSite, height, locationAlias, ipAddress, description, managementMac, nicSites, nicMacs, nicIpAddress);
}


public HostServer findByAlias(String alias){
    return hostServerDao.findByAlias(alias);
}


public HostServer findByDisplayName(String displayName){
    return hostServerDao.findByDisplayName(displayName);
}


public boolean export(){
    Iterable<HostServer> hosts = hostServerDao.findAll();
    PoiUtil.writeExcel("D:/Host_Vm.xls", "宿主机及其虚拟机关系表", new String[] { "宿主机IP", "DisplayName", "虚拟机IP" }, hosts);
    return true;
}


@Transactional(readOnly = false)
public boolean write(){
    Iterable<HostServer> hosts = hostServerDao.findAll();
    PoiUtil.writeExcel("D:/Host_Vm.xls", "宿主机及其虚拟机关系表", new String[] { "宿主机IP", "DisplayName", "虚拟机IP" }, hosts);
    return true;
}


@Transactional(readOnly = false)
public HostServer saveOrUpdate(HostServer hostServer){
    return hostServerDao.save(hostServer);
}


}