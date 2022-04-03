package com.gs.service.impl;
 import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainRecordDAO;
import com.gs.service.MaintainRecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MaintainRecordServiceImpl implements MaintainRecordService{

@Resource
 private  MaintainRecordDAO maintainRecordDAO;


@Override
public int countSix(User user,String actualEndTime){
    return maintainRecordDAO.countSix(user, actualEndTime);
}


@Override
public List<MaintainRecord> queryByOwner(Pager pager,String userId){
    return maintainRecordDAO.queryByOwner(pager, userId);
}


public List<MaintainRecord> queryByPagerDisable(Pager pager){
    return maintainRecordDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MaintainRecord> list){
    return maintainRecordDAO.batchInsert(list);
}


public int batchUpdate(List<MaintainRecord> list){
    return maintainRecordDAO.batchUpdate(list);
}


@Override
public List<MaintainRecord> queryByPagerRemindNo(Pager pager){
    return maintainRecordDAO.queryByPagerRemindNo(pager);
}


@Override
public List<MaintainRecord> queryByPagerSuccess(Pager pager){
    return maintainRecordDAO.queryByPagerSuccess(pager);
}


public int insert(MaintainRecord maintainRecord){
    return maintainRecordDAO.insert(maintainRecord);
}


public int update(MaintainRecord maintainRecord){
    return maintainRecordDAO.update(maintainRecord);
}


public int countByBlurredByRemind(MaintainRecord maintainRecord,User user){
    return maintainRecordDAO.countByBlurredByRemind(maintainRecord, user);
}


public List<MaintainRecord> queryAll(String status){
    return maintainRecordDAO.queryAll(status);
}


public int delete(MaintainRecord maintainRecord){
    return maintainRecordDAO.delete(maintainRecord);
}


@Override
public void updateCurrentStatus(String currentStatus,String recordId){
    maintainRecordDAO.updateCurrentStatus(currentStatus, recordId);
}


@Override
public int countByOwner(String userId){
    return maintainRecordDAO.countByOwner(userId);
}


public int batchDelete(List<MaintainRecord> list){
    return maintainRecordDAO.batchDelete(list);
}


@Override
public List<MaintainRecord> queryCheckinAll(String userIds){
    return maintainRecordDAO.queryCheckinAll(userIds);
}


@Override
public void updateActualEndTime(String maintainRecordId){
    maintainRecordDAO.updateActualEndTime(maintainRecordId);
}


public int inactive(String id){
    return maintainRecordDAO.inactive(id);
}


public int countByBlurred(MaintainRecord maintainRecord,User user){
    return maintainRecordDAO.countByBlurred(maintainRecord, user);
}


@Override
public int countByRemindNo(User user){
    return maintainRecordDAO.countByRemindNo(user);
}


@Override
public List<MaintainRecord> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type){
    return maintainRecordDAO.queryByCondition(start, end, companyId, maintainOrFix, type);
}


@Override
public void updatePickupTime(String maintainRecordId){
    maintainRecordDAO.updatePickupTime(maintainRecordId);
}


public MaintainRecord query(MaintainRecord maintainRecord){
    return maintainRecordDAO.query(maintainRecord);
}


public int count(User user){
    return maintainRecordDAO.count(user);
}


@Override
public int countByRemindYes(User user){
    return maintainRecordDAO.countByRemindYes(user);
}


public int active(String id){
    return maintainRecordDAO.active(id);
}


@Override
public List<MaintainRecord> queryByPagerRemindYes(Pager pager){
    return maintainRecordDAO.queryByPagerRemindYes(pager);
}


public List<MaintainRecord> blurredQuery(Pager pager,MaintainRecord maintainRecord){
    return maintainRecordDAO.blurredQuery(pager, maintainRecord);
}


@Override
public List<MaintainRecord> queryByBackstage(Pager pager){
    return null;
}


public List<MaintainRecord> queryByStatus(String status){
    return maintainRecordDAO.queryAll(status);
}


public List<MaintainRecord> blurredQueryByRemind(Pager pager,MaintainRecord maintainRecord){
    return maintainRecordDAO.blurredQueryByRemind(pager, maintainRecord);
}


public int deleteById(String id){
    return maintainRecordDAO.deleteById(id);
}


public int countByDisable(User user){
    return maintainRecordDAO.countByDisable(user);
}


@Override
public List<MaintainRecord> queryByPagerSix(Pager pager,String actualEndTime){
    return maintainRecordDAO.queryByPagerSix(pager, actualEndTime);
}


public MaintainRecord queryById(String id){
    return maintainRecordDAO.queryById(id);
}


public List<MaintainRecord> queryByPager(Pager pager){
    return maintainRecordDAO.queryByPager(pager);
}


@Override
public int countSuccess(User user){
    return maintainRecordDAO.countSuccess(user);
}


}