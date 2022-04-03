package com.sobey.cmop.mvc.service.onecmdb;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.onecmdb.core.utils.bean.CiBean;
import org.onecmdb.core.utils.bean.ValueBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.HostServerConstant;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.StorageConstant;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.EipPortItem;
import com.sobey.cmop.mvc.entity.ElbPortItem;
import com.sobey.cmop.mvc.entity.EsgRuleItem;
import com.sobey.cmop.mvc.entity.HostServer;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.Location;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.Nic;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.framework.utils.StringCommonUtils;
import com.sobey.cmop.mvc.DTO.HostServer;
import com.sobey.cmop.mvc.DTO.ServiceTag;
import com.sobey.cmop.mvc.DTO.IpPool;
import com.sobey.cmop.mvc.DTO.Location;
import com.sobey.cmop.mvc.DTO.Nic;
import com.sobey.cmop.mvc.DTO.ComputeItem;
import com.sobey.cmop.mvc.DTO.Vlan;
import com.sobey.cmop.mvc.DTO.NetworkEsgItem;
import com.sobey.cmop.mvc.DTO.StorageItem;
import com.sobey.cmop.mvc.DTO.EsgRuleItem;
@Service
public class OneCmdbUtilService extends BaseSevcie{

 private  Logger logger;


public boolean saveServerPortByHostServerToOneCMDB(HostServer hostServer){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    /**
     * 管理口网卡默认的网卡号: 111
     */
    String managerSite = "111";
    CiBean ci = new CiBean("ServerPort", "ServerPort" + hostServer.getAlias(), false);
    ci.addAttributeValue(new ValueBean("IPAddress", "IPAddress-" + hostServer.getIpAddress(), true));
    ci.addAttributeValue(new ValueBean("Server", "Server" + hostServer.getAlias(), true));
    ci.addAttributeValue(new ValueBean("Sit", managerSite, false));
    ci.addAttributeValue(new ValueBean("MacAddress", hostServer.getManagementMac(), false));
    ci.addAttributeValue(new ValueBean("ConnectedTo", hostServer.getSwitchName(), false));
    ciBeanList.add(ci);
    return OneCmdbService.update(ciBeanList);
}


public void saveELBToOneCMDB(Collection<NetworkElbItem> networkElbItems,ServiceTag serviceTag){
    for (NetworkElbItem networkElbItem : networkElbItems) {
        this.saveELBToOneCMDB(networkElbItem, serviceTag);
    }
}


public boolean saveServiceTagToOneCMDB(ServiceTag serviceTag){
    List<CiBean> ciList = new ArrayList<CiBean>();
    CiBean ci = new CiBean("ApplicationService", serviceTag.getIdentifier(), false);
    ci.addAttributeValue(new ValueBean("Name", serviceTag.getName(), false));
    ci.addAttributeValue(new ValueBean("BelongsTo", serviceTag.getUser().getName(), false));
    ci.setDescription(serviceTag.getDescription());
    ciList.add(ci);
    return OneCmdbService.update(ciList);
}


public void deleteIpPoolToOneCMDB(IpPool ipPool){
    if (ipPool != null) {
        List<CiBean> ciBeanList = new ArrayList<CiBean>();
        CiBean router = new CiBean(this.getPoolNameFromOneCMDBByPoolType(ipPool.getPoolType()), "IPAddress-" + ipPool.getIpAddress(), false);
        ciBeanList.add(router);
        OneCmdbService.delete(ciBeanList);
    }
}


public void deleteELBToOneCMDB(NetworkElbItem networkElbItem){
    if (networkElbItem != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean("ELB", networkElbItem.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public boolean saveLocationToOneCMDB(Location location){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    CiBean router = new CiBean("Location", location.getAlias(), false);
    router.addAttributeValue(new ValueBean("Name", location.getName(), false));
    router.addAttributeValue(new ValueBean("City", location.getCity(), false));
    router.addAttributeValue(new ValueBean("Postal_Code", location.getPostcode(), false));
    router.addAttributeValue(new ValueBean("Street_Address", location.getAddress(), false));
    router.addAttributeValue(new ValueBean("TelePhone", location.getTelephone(), false));
    ciBeanList.add(router);
    return OneCmdbService.update(ciBeanList);
}


public void saveDNSToOneCMDB(Collection<NetworkDnsItem> networkDnsItems,ServiceTag serviceTag){
    for (NetworkDnsItem networkDnsItem : networkDnsItems) {
        this.saveDNSToOneCMDB(networkDnsItem, serviceTag);
    }
}


public void saveComputeToOneCMDB(Collection<ComputeItem> computeItems,ServiceTag serviceTag){
    for (ComputeItem computeItem : computeItems) {
        this.saveComputeToOneCMDB(computeItem, serviceTag);
    }
}


public void deleteServerPortToOneCMDB(HostServer hostServer){
    if (hostServer != null) {
        List<CiBean> ciBeanList = new ArrayList<CiBean>();
        List<Nic> nics = comm.hostServerService.getNicByhostServerId(hostServer.getId());
        for (Nic nic : nics) {
            CiBean ci = new CiBean("ServerPort", "ServerPort" + nic.getAlias(), false);
            ciBeanList.add(ci);
        }
        OneCmdbService.delete(ciBeanList);
    }
}


public String getPoolNameFromOneCMDBByPoolType(Integer poolType){
    String nodeName = "";
    if (IpPoolConstant.PoolType.互联网IP池.toInteger().equals(poolType)) {
        nodeName = "InternetPool";
    } else if (IpPoolConstant.PoolType.公网IP池.toInteger().equals(poolType)) {
        nodeName = "PublicPool";
    } else if (IpPoolConstant.PoolType.私网IP池.toInteger().equals(poolType)) {
        nodeName = "PrivatePool";
    } else if (IpPoolConstant.PoolType.虚拟负载IP池.toInteger().equals(poolType)) {
        nodeName = "VIPPool";
    }
    return nodeName;
}


public void deleteComputeItemToOneCMDB(ComputeItem computeItem){
    if (computeItem != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean(ComputeConstant.ComputeType.PCS.toInteger().equals(computeItem.getComputeType()) ? "PCS" : "ECS", computeItem.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public Map getOsStorageFromOnecmdb(){
    return OneCmdbService.findCiByText("NFSVol", "NFS");
}


public boolean saveVlanToOneCMDB(Vlan vlan){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    CiBean router = new CiBean("Vlans", vlan.getAlias(), false);
    router.addAttributeValue(new ValueBean("Name", vlan.getName(), false));
    router.addAttributeValue(new ValueBean("Location", vlan.getLocation().getAlias(), true));
    router.setDescription(vlan.getDescription());
    ciBeanList.add(router);
    return OneCmdbService.update(ciBeanList);
}


public Map getNfsHardWareFromOnecmdb(){
    return OneCmdbService.findCiByText("Controller");
}


public boolean saveIpPoolToOneCMDB(List<IpPool> ipPools,Integer poolType){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    for (IpPool ipPool : ipPools) {
        CiBean router = new CiBean(this.getPoolNameFromOneCMDBByPoolType(poolType), "IPAddress-" + ipPool.getIpAddress(), false);
        router.addAttributeValue(new ValueBean("IPAddress", ipPool.getIpAddress(), false));
        // router.addAttributeValue(new ValueBean("NetMask",
        // "255.255.254.1", false));
        // router.addAttributeValue(new ValueBean("GateWay", "172.0.0.1",
        // false));
        router.addAttributeValue(new ValueBean("Location", ipPool.getVlan().getLocation().getAlias(), true));
        router.addAttributeValue(new ValueBean("Vlan", ipPool.getVlan().getAlias(), true));
        // TODO IP状态待确定.看IP状态是否需要同步.
        router.addAttributeValue(new ValueBean("Status", "Status1341922499992", true));
        ciBeanList.add(router);
    }
    return OneCmdbService.update(ciBeanList);
}


public void deleteESGToOneCMDB(NetworkEsgItem networkEsgItem){
    if (networkEsgItem != null) {
        List<CiBean> ciBeanList = new ArrayList<CiBean>();
        CiBean router = new CiBean("ESG", networkEsgItem.getIdentifier(), false);
        ciBeanList.add(router);
        OneCmdbService.delete(ciBeanList);
    }
}


public void deleteHostServerToOneCMDB(HostServer hostServer){
    if (hostServer != null) {
        List<CiBean> ciBeanList = new ArrayList<CiBean>();
        CiBean router = new CiBean("Server", "Server" + hostServer.getAlias(), false);
        CiBean ci = new CiBean("ServerPort", "ServerPort" + hostServer.getAlias(), false);
        ciBeanList.add(router);
        ciBeanList.add(ci);
        OneCmdbService.delete(ciBeanList);
        // ServerPort
        this.deleteServerPortToOneCMDB(hostServer);
    }
}


public boolean deleteLocationToOneCMDB(Location location){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    CiBean router = new CiBean("Location", location.getAlias(), false);
    ciBeanList.add(router);
    // 删除Location时,也会将关联的vlan也删除.
    for (Vlan vlan : location.getVlans()) {
        CiBean vlanRouter = new CiBean("Vlans", vlan.getAlias(), false);
        ciBeanList.add(vlanRouter);
    }
    return OneCmdbService.delete(ciBeanList);
}


public void saveStorageToOneCMDB(Collection<StorageItem> storageItems,ServiceTag serviceTag){
    for (StorageItem storageItem : storageItems) {
        this.saveStorageToOneCMDB(storageItem, serviceTag);
    }
}


public void saveEIPToOneCMDB(Collection<NetworkEipItem> networkEipItems,ServiceTag serviceTag){
    for (NetworkEipItem networkEipItem : networkEipItems) {
        this.saveEIPToOneCMDB(networkEipItem, serviceTag);
    }
}


public void deleteEIPToOneCMDB(NetworkEipItem networkEipItem){
    if (networkEipItem != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean("EIP", networkEipItem.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public void deleteStorageItemToOneCMDB(StorageItem storageItem){
    if (storageItem != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean(StorageConstant.StorageType.Fimas_高吞吐量.toInteger().equals(storageItem.getStorageType()) ? "FimasVol" : "NFSVol", storageItem.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public boolean saveESGToOneCMDB(NetworkEsgItem networkEsgItem){
    List<CiBean> ciList = new ArrayList<CiBean>();
    // 规则：协议,端口范围,访问源.如果有多条规则，则按","隔开
    String protocol = "";
    String portRange = "";
    String visitSource = "";
    String visitTarget = "";
    List<EsgRuleItem> esgRuleItems = comm.esgService.getEsgRuleItemListByEsgId(networkEsgItem.getId());
    for (EsgRuleItem esgRuleItem : esgRuleItems) {
        protocol += esgRuleItem.getProtocol() + ",";
        portRange += esgRuleItem.getPortRange() + ",";
        visitSource += esgRuleItem.getVisitSource() + ",";
        visitTarget += esgRuleItem.getVisitTarget() + ",";
    }
    CiBean ci = new CiBean("ESG", networkEsgItem.getIdentifier(), false);
    // BelongsTo：属于某个申请人，先写文本
    ci.addAttributeValue(new ValueBean("BelongsTo", networkEsgItem.getUser().getName(), false));
    ci.addAttributeValue(new ValueBean("Name", networkEsgItem.getIdentifier(), false));
    ci.addAttributeValue(new ValueBean("Type", StringCommonUtils.replaceAndSubstringText(protocol, ",", ","), false));
    ci.addAttributeValue(new ValueBean("Port", StringCommonUtils.replaceAndSubstringText(portRange, ",", ","), false));
    ci.addAttributeValue(new ValueBean("SourceIP", StringCommonUtils.replaceAndSubstringText(visitSource, ",", ","), false));
    ci.addAttributeValue(new ValueBean("TargetIP", StringCommonUtils.replaceAndSubstringText(visitTarget, ",", ","), false));
    ci.setDescription(networkEsgItem.getDescription());
    ciList.add(ci);
    return OneCmdbService.update(ciList);
}


public void deleteDNSToOneCMDB(NetworkDnsItem networkDnsItem){
    if (networkDnsItem != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean("DNS", networkDnsItem.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public Map getFimasHardWareFromOnecmdb(){
    return OneCmdbService.findCiByText("Fimas");
}


public boolean deleteVlanToOneCMDB(Vlan vlan){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    CiBean router = new CiBean("Vlans", vlan.getAlias(), false);
    ciBeanList.add(router);
    return OneCmdbService.delete(ciBeanList);
}


public void deleteServiceTagToOneCMDB(ServiceTag serviceTag){
    if (serviceTag != null) {
        List<CiBean> ciList = new ArrayList<CiBean>();
        CiBean router = new CiBean("ApplicationService", serviceTag.getIdentifier(), false);
        ciList.add(router);
        OneCmdbService.delete(ciList);
    }
}


public boolean saveHostServerToOneCMDB(HostServer hostServer){
    List<CiBean> ciList = new ArrayList<CiBean>();
    try {
        CiBean router = new CiBean("Server", "Server" + hostServer.getAlias(), false);
        router.addAttributeValue(new ValueBean("Location", hostServer.getLocationAlias(), true));
        router.addAttributeValue(new ValueBean("Type", HostServerConstant.HostServerType.map.get(hostServer.getServerType()), false));
        router.addAttributeValue(new ValueBean("Site", hostServer.getSite(), false));
        router.addAttributeValue(new ValueBean("HostName", hostServer.getDisplayName(), false));
        router.addAttributeValue(new ValueBean("Height", hostServer.getHeight(), false));
        router.addAttributeValue(new ValueBean("Name", hostServer.getServerModel().getName(), false));
        // 架
        router.addAttributeValue(new ValueBean("Rack", hostServer.getRackAlias(), true));
        router.addAttributeValue(new ValueBean("Company", hostServer.getServerModel().getCompanyAlias(), true));
        router.addAttributeValue(new ValueBean("Model", hostServer.getServerModel().getName(), false));
        router.addAttributeValue(new ValueBean("CpuNum", hostServer.getServerModel().getCpu().toString(), false));
        router.addAttributeValue(new ValueBean("DiskNum", hostServer.getServerModel().getDisk().toString(), false));
        router.addAttributeValue(new ValueBean("PortNum", hostServer.getServerModel().getPort().toString(), false));
        router.addAttributeValue(new ValueBean("PciNum", hostServer.getServerModel().getPci().toString(), false));
        router.addAttributeValue(new ValueBean("MemNum", hostServer.getServerModel().getMemory().toString(), false));
        router.setDescription(hostServer.getDescription());
        ciList.add(router);
        OneCmdbService.update(ciList);
        // hostServer中的网卡写入ServerPort
        this.saveServerPortByHostServerToOneCMDB(hostServer);
        // ServerPort
        this.saveServerPortToOneCMDB(hostServer);
    } catch (Exception e) {
        logger.error("HostServer 插入oneCMDB失败!");
        e.printStackTrace();
        return false;
    }
    return true;
}


public boolean saveServerPortToOneCMDB(HostServer hostServer){
    List<CiBean> ciBeanList = new ArrayList<CiBean>();
    List<Nic> nics = comm.hostServerService.getNicByhostServerId(hostServer.getId());
    for (Nic nic : nics) {
        CiBean ci = new CiBean("ServerPort", "ServerPort" + nic.getAlias(), false);
        ci.addAttributeValue(new ValueBean("IPAddress", "IPAddress-" + nic.getIpAddress(), true));
        ci.addAttributeValue(new ValueBean("Server", "Server" + hostServer.getAlias(), true));
        ci.addAttributeValue(new ValueBean("Sit", nic.getSite(), false));
        ci.addAttributeValue(new ValueBean("MacAddress", nic.getMac(), false));
        ci.addAttributeValue(new ValueBean("ConnectedTo", hostServer.getSwitchName(), false));
        ciBeanList.add(ci);
    }
    return OneCmdbService.update(ciBeanList);
}


}