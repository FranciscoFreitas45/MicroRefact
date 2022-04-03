package com.sobey.cmop.mvc.service.iaas;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.ElbPortItemDao;
import com.sobey.cmop.mvc.dao.NetworkElbItemDao;
import com.sobey.cmop.mvc.dao.custom.BasicUnitDaoCustom;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.ElbPortItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.DTO.Resources;
@Service
@Transactional(readOnly = true)
public class ElbService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  NetworkElbItemDao networkElbItemDao;

@Resource
 private  ElbPortItemDao elbPortItemDao;

@Resource
 private  BasicUnitDaoCustom basicUnitDao;


@Transactional(readOnly = false)
public ElbPortItem saveOrUpdateElbPortItem(ElbPortItem elbPortItem){
    return elbPortItemDao.save(elbPortItem);
}


public NetworkElbItem getNetworkElbItem(Integer id){
    return networkElbItemDao.findOne(id);
}


@Transactional(readOnly = false)
public void updateELBToApply(NetworkElbItem networkElbItem,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds){
    // Step.1 将ELB下的所有映射信息删除.
    this.deleteElbPortItem(this.getElbPortItemListByElbId(networkElbItem.getId()));
    // Step.2 关联实例
    List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
    if (computeIds != null) {
        for (String computeId : computeIds) {
            computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
        }
    }
    networkElbItem.setComputeItemList(computeItemList);
    this.saveOrUpdate(networkElbItem);
    // Step.3 ELB的端口映射
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            ElbPortItem elbPortItem = new ElbPortItem(networkElbItem, protocols[i], sourcePorts[i], targetPorts[i]);
            this.saveOrUpdateElbPortItem(elbPortItem);
        }
    }
}


public List<ElbPortItem> getElbPortItemListByElbId(Integer elbId){
    return elbPortItemDao.findByNetworkElbItemId(elbId);
}


@Transactional(readOnly = false)
public void deleteNetworkElbItem(Integer id){
    networkElbItemDao.delete(id);
}


public List<NetworkElbItem> getNetworkElbItemListByUserId(Integer userId){
    return networkElbItemDao.findByApplyUserId(userId);
}


public List<NetworkElbItem> getElbListByApplyId(Integer applyId){
    return networkElbItemDao.findByApplyId(applyId);
}


@Transactional(readOnly = false)
public void deleteElbPortItem(Collection<ElbPortItem> elbPortItems){
    elbPortItemDao.delete(elbPortItems);
}


@Transactional(readOnly = false)
public void saveResourcesByElb(Resources resources,Integer serviceTagId,String keepSession,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds,String changeDescription){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    NetworkElbItem networkElbItem = this.getNetworkElbItem(resources.getServiceId());
    List<ElbPortItem> elbPortItems = this.getElbPortItemListByElbId(networkElbItem.getId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareElb(resources, change, networkElbItem, elbPortItems, keepSession, protocols, sourcePorts, targetPorts, computeIds);
    // 删除变更前的端口映射
    this.deleteElbPortItem(elbPortItems);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    networkElbItem.setKeepSession(NetworkConstant.KeepSession.保持.toString().equals(keepSession) ? true : false);
    // 关联实例
    List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
    if (computeIds != null) {
        for (int i = 0; i < computeIds.length; i++) {
            ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
            computeItemList.add(computeItem);
        }
    }
    networkElbItem.setComputeItemList(computeItemList);
    this.saveOrUpdate(networkElbItem);
    // ELB的端口映射
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            ElbPortItem elbPortItem = new ElbPortItem(networkElbItem, protocols[i], sourcePorts[i], targetPorts[i]);
            this.saveOrUpdateElbPortItem(elbPortItem);
        }
    }
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public NetworkElbItem saveOrUpdate(NetworkElbItem networkElbItem){
    return networkElbItemDao.save(networkElbItem);
}


@Transactional(readOnly = false)
public void saveELBToApply(Integer applyId,String[] keepSessions,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds){
    Apply apply = comm.applyService.getApply(applyId);
    for (int i = 0; i < keepSessions.length; i++) {
        String identifier = comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.ELB.toInteger());
        NetworkElbItem networkElbItem = new NetworkElbItem();
        networkElbItem.setApply(apply);
        networkElbItem.setIdentifier(identifier);
        networkElbItem.setVirtualIp(IpPoolConstant.DEFAULT_IPADDRESS);
        networkElbItem.setOldIp(IpPoolConstant.DEFAULT_IPADDRESS);
        networkElbItem.setKeepSession(NetworkConstant.KeepSession.保持.toString().equals(keepSessions[i]) ? true : false);
        // 关联实例
        if (computeIds != null && computeIds.length > 0) {
            List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
            // 解决select2框架中,多重数组的重复问题.
            if (keepSessions.length == 1) {
                // 通过"-"获得存储空间挂载的实例ID
                for (String computeId : computeIds) {
                    computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
                }
            } else if (keepSessions.length > 1) {
                // 通过"-"获得存储空间挂载的实例ID
                String[] computeIdArray = StringUtils.split(computeIds[i], ",");
                for (String computeId : computeIdArray) {
                    computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
                }
            }
            networkElbItem.setComputeItemList(computeItemList);
        }
        this.saveOrUpdate(networkElbItem);
        // ELB的端口映射
        if (sourcePorts != null && sourcePorts.length > 0) {
            String[] protocolArray = StringUtils.split(protocols[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            String[] sourcePortArray = StringUtils.split(sourcePorts[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            String[] targetPortArray = StringUtils.split(targetPorts[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            for (int j = 0; j < protocolArray.length; j++) {
                ElbPortItem elbPortItem = new ElbPortItem(networkElbItem, protocolArray[j], sourcePortArray[j], targetPortArray[j]);
                this.saveOrUpdateElbPortItem(elbPortItem);
            }
        }
    }
}


}