package com.sobey.cmop.mvc.service.resource;
 import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.dao.ChangeDao;
import com.sobey.cmop.mvc.dao.ChangeItemDao;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ChangeItem;
import com.sobey.cmop.mvc.entity.Resources;
@Service
@Transactional(readOnly = true)
public class ChangeServcie extends BaseSevcie{

 private  Logger logger;

@Resource
 private  ChangeDao changeDao;

@Resource
 private  ChangeItemDao changeItemDao;


@Transactional(readOnly = false)
public ChangeItem saveOrUpdateChangeItem(ChangeItem changeItem){
    return changeItemDao.save(changeItem);
}


public ChangeItem getChangeItem(Integer id){
    return changeItemDao.findOne(id);
}


public List<ChangeItem> getChangeItemListByChangeId(Integer changeId){
    return changeItemDao.findByChangeId(changeId);
}


@Transactional(readOnly = false)
public Change saveOrUpdateChange(Change change){
    return changeDao.save(change);
}


public Change getChange(Integer id){
    return changeDao.findOne(id);
}


public List<Change> getChangeListByResourcesId(Integer resourcesId){
    return changeDao.findByResources_Id(resourcesId);
}


public List<ChangeItem> getChangeItemListByChangeIdAndFieldName(Integer changeId,String fieldName){
    return changeItemDao.findByChangeIdAndFieldNameOrderByIdDesc(changeId, fieldName);
}


public Change findChangeByResourcesId(Integer resourcesId){
    return changeDao.findByResourcesIdAndSubResourcesIdIsNull(resourcesId);
}


public Change findChangeBySubResourcesId(Integer resourcesId,Integer subResourcesId){
    return changeDao.findByResourcesIdAndSubResourcesId(resourcesId, subResourcesId);
}


@Transactional(readOnly = false)
public void deleteChange(Collection<Change> changes){
    changeDao.delete(changes);
}


@Transactional(readOnly = false)
public Change saveOrUpdateChangeByResources(Resources resources,Integer subResourcesId,String description){
    Change change = null;
    // step.1 查找指定资源Resources的change
    if (subResourcesId == null) {
        change = comm.changeServcie.findChangeByResourcesId(resources.getId());
    } else {
        change = comm.changeServcie.findChangeBySubResourcesId(resources.getId(), subResourcesId);
    }
    if (change == null) {
        // step.2 不存在创建一个新的Change (表示该资源之前没有变更记录,即该资源未变更过.)
        change = new Change(resources, comm.accountService.getCurrentUser(), new Date());
        change.setDescription(description);
    } else {
        // step.3 如果存在则更新老的Change(主要就是更新变更信息).
        change.setChangeTime(new Date());
        change.setDescription(description);
    }
    change.setSubResourcesId(subResourcesId);
    return this.saveOrUpdateChange(change);
}


}