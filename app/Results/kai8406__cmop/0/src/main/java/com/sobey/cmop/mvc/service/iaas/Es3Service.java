package com.sobey.cmop.mvc.service.iaas;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.StorageItemDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.StorageItem;
@Service
@Transactional(readOnly = true)
public class Es3Service extends BaseSevcie{

 private  Logger logger;

@Resource
 private  StorageItemDao storageItemDao;


@Transactional(readOnly = false)
public void saveES3ToApply(Integer applyId,String[] spaces,String[] storageTypes,String[] computeIds){
    Apply apply = comm.applyService.getApply(applyId);
    for (int i = 0; i < storageTypes.length; i++) {
        StorageItem storageItem = new StorageItem();
        String identifier = comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.ES3.toInteger());
        storageItem.setIdentifier(identifier);
        // 存储空间大小
        storageItem.setSpace(Integer.parseInt(spaces[i]));
        storageItem.setApply(apply);
        storageItem.setStorageType(Integer.parseInt(storageTypes[i]));
        if (computeIds != null && computeIds.length > 0) {
            List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
            if (storageTypes.length == 1) {
                for (String computeId : computeIds) {
                    computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
                }
            } else if (storageTypes.length > 1) {
                // 通过"-"获得存储空间挂载的实例ID
                String[] computeIdArray = StringUtils.split(computeIds[i], ",");
                for (String computeId : computeIdArray) {
                    computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
                }
            }
            storageItem.setComputeItemList(computeItemList);
        }
        this.saveOrUpdate(storageItem);
    }
}


@Transactional(readOnly = false)
public void saveResourcesByStorage(Resources resources,Integer serviceTagId,Integer storageType,Integer space,String[] computeIds,String changeDescription){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    StorageItem storageItem = this.getStorageItem(resources.getServiceId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareStorage(resources, change, storageItem, storageType, space, computeIds);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    storageItem.setStorageType(storageType);
    storageItem.setSpace(space);
    List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
    if (computeIds != null && computeIds.length > 0) {
        for (int i = 0; i < computeIds.length; i++) {
            ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
            computeItemList.add(computeItem);
        }
    }
    storageItem.setComputeItemList(computeItemList);
    // 更新storageItem
    this.saveOrUpdate(storageItem);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


public StorageItem getStorageItem(Integer id){
    return storageItemDao.findOne(id);
}


@Transactional(readOnly = false)
public void updateES3ToApply(StorageItem storageItem,Integer space,Integer storageType,String[] computeIds){
    List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
    if (computeIds != null) {
        for (String computeId : computeIds) {
            computeItemList.add(comm.computeService.getComputeItem(Integer.valueOf(computeId)));
        }
    }
    storageItem.setSpace(space);
    storageItem.setStorageType(storageType);
    storageItem.setComputeItemList(computeItemList);
    comm.es3Service.saveOrUpdate(storageItem);
}


@Transactional(readOnly = false)
public void deleteStorageItem(Integer id){
    storageItemDao.delete(id);
}


@Transactional(readOnly = false)
public StorageItem saveOrUpdate(StorageItem storageItem){
    return storageItemDao.save(storageItem);
}


public List<StorageItem> getStorageListByApplyId(Integer applyId){
    return storageItemDao.findByApplyId(applyId);
}


}