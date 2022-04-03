package com.sobey.cmop.mvc.service.iaas;
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
import com.sobey.cmop.mvc.dao.EipPortItemDao;
import com.sobey.cmop.mvc.dao.NetworkEipItemDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.EipPortItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
@Service
@Transactional(readOnly = true)
public class EipService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  NetworkEipItemDao networkEipItemDao;

@Resource
 private  EipPortItemDao eipPortItemDao;


@Transactional(readOnly = false)
public void updateEIPToApply(NetworkEipItem networkEipItem,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts){
    // Step.1
    this.deleteEipPortItem(this.getEipPortItemListByEipId(networkEipItem.getId()));
    // Step.2
    // 判断关联类型,根据关联类型和关联ID获得对象后封装至NetworkEipItem.
    networkEipItem = this.fillComputeOrElbToNetworkEipItem(networkEipItem, linkType, linkId);
    // Step.3
    this.saveOrUpdate(networkEipItem);
    // ELB的端口映射
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            EipPortItem eipPortItem = new EipPortItem(networkEipItem, protocols[i], sourcePorts[i], targetPorts[i]);
            this.saveOrUpdateEipPortItem(eipPortItem);
        }
    }
}


@Transactional(readOnly = false)
public void deleteNetworkEipItem(Integer id){
    if (this.getNetworkEipItem(id) != null) {
        networkEipItemDao.delete(id);
    }
}


@Transactional(readOnly = false)
public void saveEIPToApply(Integer applyId,String[] ispTypes,String[] linkTypes,String[] linkIds,String[] protocols,String[] sourcePorts,String[] targetPorts){
    Apply apply = comm.applyService.getApply(applyId);
    logger.info("创建EIP的数量:" + ispTypes.length);
    for (int i = 0; i < ispTypes.length; i++) {
        String identifier = comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.EIP.toInteger());
        NetworkEipItem networkEipItem = new NetworkEipItem();
        networkEipItem.setApply(apply);
        networkEipItem.setIdentifier(identifier);
        networkEipItem.setIpAddress(IpPoolConstant.DEFAULT_IPADDRESS);
        networkEipItem.setOldIp(IpPoolConstant.DEFAULT_IPADDRESS);
        networkEipItem.setIspType(Integer.valueOf(ispTypes[i]));
        // 判断关联类型,根据关联类型和关联ID获得对象后封装至NetworkEipItem.
        if (linkIds != null && linkIds.length > 0) {
            networkEipItem = this.fillComputeOrElbToNetworkEipItem(networkEipItem, linkTypes[i], linkIds[i]);
        }
        this.saveOrUpdate(networkEipItem);
        // EIP的端口映射
        if (sourcePorts != null && sourcePorts.length > 0) {
            String[] protocolArray = StringUtils.split(protocols[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            String[] sourcePortArray = StringUtils.split(sourcePorts[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            String[] targetPortArray = StringUtils.split(targetPorts[i], NetworkConstant.SEPARATE_PORT_SYMBOL);
            for (int j = 0; j < protocolArray.length; j++) {
                EipPortItem eipPortItem = new EipPortItem(networkEipItem, protocolArray[j], sourcePortArray[j], targetPortArray[j]);
                this.saveOrUpdateEipPortItem(eipPortItem);
            }
        }
    }
}


public List<NetworkEipItem> getEipListByApplyId(Integer applyId){
    return networkEipItemDao.findByApplyId(applyId);
}


@Transactional(readOnly = false)
public EipPortItem saveOrUpdateEipPortItem(EipPortItem eipPortItem){
    return eipPortItemDao.save(eipPortItem);
}


public List<NetworkEipItem> getNetworkEipItemListByComputeItemId(Integer computeItemId){
    return networkEipItemDao.findByComputeItemId(computeItemId);
}


public List<EipPortItem> getEipPortItemListByEipId(Integer eipId){
    return eipPortItemDao.findByNetworkEipItemId(eipId);
}


@Transactional(readOnly = false)
public void deleteEipPortItem(Collection<EipPortItem> eipPortItems){
    eipPortItemDao.delete(eipPortItems);
}


public NetworkEipItem fillComputeOrElbToNetworkEipItem(NetworkEipItem networkEipItem,String linkType,String linkId){
    if (NetworkConstant.LinkType.关联实例.toString().equals(linkType)) {
        // 关联实例
        if (!"".equals(linkId)) {
            networkEipItem.setComputeItem(comm.computeService.getComputeItem(Integer.valueOf(linkId)));
        } else {
            networkEipItem.setComputeItem(null);
        }
        networkEipItem.setNetworkElbItem(null);
    } else if (NetworkConstant.LinkType.关联ELB.toString().equals(linkType) && !"".equals(linkId)) {
        // 关联ELB
        if (!"".equals(linkId)) {
            networkEipItem.setNetworkElbItem(comm.elbService.getNetworkElbItem(Integer.valueOf(linkId)));
        } else {
            networkEipItem.setNetworkElbItem(null);
        }
        networkEipItem.setComputeItem(null);
    }
    return networkEipItem;
}


public List<NetworkEipItem> getNetworkEipItemListByUserId(Integer userId){
    return networkEipItemDao.findByApplyUserId(userId);
}


public NetworkEipItem getNetworkEipItem(Integer id){
    return networkEipItemDao.findOne(id);
}


@Transactional(readOnly = false)
public void saveResourcesByEip(Resources resources,Integer serviceTagId,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts,String changeDescription){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    NetworkEipItem networkEipItem = this.getNetworkEipItem(resources.getServiceId());
    List<EipPortItem> eipPortItems = this.getEipPortItemListByEipId(networkEipItem.getId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareEip(resources, change, networkEipItem, eipPortItems, linkType, linkId, protocols, sourcePorts, targetPorts);
    // 删除变更前的端口映射
    this.deleteEipPortItem(eipPortItems);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    // 判断关联类型,根据关联类型和关联ID获得对象后封装至NetworkEipItem.
    networkEipItem = this.fillComputeOrElbToNetworkEipItem(networkEipItem, linkType, linkId);
    this.saveOrUpdate(networkEipItem);
    // EIP的端口映射
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            EipPortItem eipPortItem = new EipPortItem(networkEipItem, protocols[i], sourcePorts[i], targetPorts[i]);
            this.saveOrUpdateEipPortItem(eipPortItem);
        }
    }
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public NetworkEipItem saveOrUpdate(NetworkEipItem networkEipItem){
    return networkEipItemDao.save(networkEipItem);
}


}