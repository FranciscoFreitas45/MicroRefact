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
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.ApplicationDao;
import com.sobey.cmop.mvc.dao.ComputeItemDao;
import com.sobey.cmop.mvc.entity.Application;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
@Service
@Transactional(readOnly = true)
public class ComputeService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  ComputeItemDao computeItemDao;

@Resource
 private  ApplicationDao applicationDao;


public List<Application> getApplicationByComputeItemId(Integer computeItemId){
    return applicationDao.findByComputeItemId(computeItemId);
}


public List<ComputeItem> getComputeListByApplyId(Integer applyId){
    return computeItemDao.findByApplyId(applyId);
}


public List<ComputeItem> wrapComputeItemToList(Apply apply,Integer computeType,String[] osTypes,String[] osBits,String[] serverTypes,String[] remarks,String[] esgIds){
    List<ComputeItem> computes = new ArrayList<ComputeItem>();
    for (int i = 0; i < osTypes.length; i++) {
        // 区分PCS和ECS然后生成标识符identifier
        Integer serviceType = ComputeConstant.ComputeType.PCS.toInteger().equals(computeType) ? ResourcesConstant.ServiceType.PCS.toInteger() : ResourcesConstant.ServiceType.ECS.toInteger();
        String identifier = comm.applyService.generateIdentifier(serviceType);
        ComputeItem computeItem = new ComputeItem();
        computeItem.setApply(apply);
        computeItem.setIdentifier(identifier);
        computeItem.setComputeType(computeType);
        computeItem.setOsType(Integer.parseInt(osTypes[i]));
        computeItem.setOsBit(Integer.parseInt(osBits[i]));
        computeItem.setServerType(Integer.parseInt(serverTypes[i]));
        computeItem.setRemark(remarks[i]);
        computeItem.setInnerIp(IpPoolConstant.DEFAULT_IPADDRESS);
        computeItem.setOldIp(IpPoolConstant.DEFAULT_IPADDRESS);
        // 分割关联esg的Id.
        if (esgIds != null && esgIds.length > 0) {
            List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
            if (osTypes.length == 1) {
                for (String esgId : esgIds) {
                    networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.parseInt(esgId)));
                }
            } else if (osTypes.length > 1) {
                String[] esgIdArray = StringUtils.split(esgIds[i], ",");
                for (String esgId : esgIdArray) {
                    networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.parseInt(esgId)));
                }
            }
            computeItem.setNetworkEsgItemList(networkEsgItemList);
        }
        computes.add(computeItem);
    }
    return computes;
}


@Transactional(readOnly = false)
public void saveResourcesByCompute(Resources resources,Integer serviceTagId,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths,String changeDescription){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    ComputeItem computeItem = comm.computeService.getComputeItem(resources.getServiceId());
    /* 比较实例资源computeItem 变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareCompute(resources, change, computeItem, osType, osBit, serverType, esgIds, remark, applicationNames, applicationVersions, applicationDeployPaths);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    computeItem.setOsType(osType);
    computeItem.setOsBit(osBit);
    computeItem.setServerType(serverType);
    computeItem.setRemark(remark);
    List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
    if (esgIds != null && esgIds.length > 0) {
        for (String esgId : esgIds) {
            networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(esgId)));
        }
    }
    computeItem.setNetworkEsgItemList(networkEsgItemList);
    // 更新compute
    this.saveOrUpdate(computeItem);
    // 更新application
    this.updateApplication(computeItem, applicationNames, applicationVersions, applicationDeployPaths);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public void saveApplication(ComputeItem computeItem,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths){
    if (applicationNames != null) {
        for (int i = 0; i < applicationNames.length; i++) {
            Application application = new Application(computeItem, applicationNames[i], applicationVersions[i], applicationDeployPaths[i]);
            applicationDao.save(application);
        }
    }
}


@Transactional(readOnly = false)
public void updateApplication(ComputeItem computeItem,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths){
    List<Application> applications = this.getApplicationByComputeItemId(computeItem.getId());
    if (!applications.isEmpty()) {
        this.deleteApplication(applications);
    }
    this.saveApplication(computeItem, applicationNames, applicationVersions, applicationDeployPaths);
}


public ComputeItem getComputeItem(Integer id){
    return computeItemDao.findOne(id);
}


public List<ComputeItem> getComputeListByUserId(Integer userId){
    return computeItemDao.findByApplyUserId(userId);
}


@Transactional(readOnly = false)
public ComputeItem updateComputeToApply(Integer computeId,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark){
    ComputeItem computeItem = comm.computeService.getComputeItem(computeId);
    computeItem.setOsType(osType);
    computeItem.setOsBit(osBit);
    computeItem.setServerType(serverType);
    computeItem.setRemark(remark);
    List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
    if (esgIds != null) {
        for (String esgId : esgIds) {
            networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(esgId)));
        }
    }
    computeItem.setNetworkEsgItemList(networkEsgItemList);
    return comm.computeService.saveOrUpdate(computeItem);
}


@Transactional(readOnly = false)
public void deleteApplication(Collection<Application> applications){
    applicationDao.delete(applications);
}


@Transactional(readOnly = false)
public void saveComputeToApply(Integer computeType,Integer applyId,String[] osTypes,String[] osBits,String[] serverTypes,String[] remarks,String[] esgIds){
    List<ComputeItem> computes = this.wrapComputeItemToList(comm.applyService.getApply(applyId), computeType, osTypes, osBits, serverTypes, remarks, esgIds);
    computeItemDao.save(computes);
}


public ComputeItem saveOrUpdate(ComputeItem computeItem){
    return computeItemDao.save(computeItem);
}


@Transactional(readOnly = false)
public void deleteCompute(Integer id){
    computeItemDao.delete(id);
}


}