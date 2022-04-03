package com.gs.dao;
 import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaintainRecordDAO extends BaseDAO<String, MaintainRecord>{


public int countSix(User user,String actualEndTime)
;

public List<MaintainRecord> queryByOwner(Pager pager,String userId)
;

public void updatePickupTime(String maintainRecordId)
;

public List<MaintainRecord> queryByPagerRemindNo(Pager pager)
;

public int countByRemindYes(User user)
;

public List<MaintainRecord> queryByPagerSuccess(Pager pager)
;

public int countByBlurredByRemind(MaintainRecord maintainRecord,User user)
;

public List<MaintainRecord> queryByPagerRemindYes(Pager pager)
;

public List<MaintainRecord> blurredQuery(Pager pager,MaintainRecord maintainRecord)
;

public void updateCurrentStatus(String currentStatus,String recordId)
;

public int countByOwner(String userId)
;

public List<MaintainRecord> queryCheckinAll(String userIds)
;

public List<MaintainRecord> queryByBackstage(Pager pager)
;

public List<MaintainRecord> blurredQueryByRemind(Pager pager,MaintainRecord maintainRecord)
;

public void updateActualEndTime(String maintainRecordId)
;

public int countByBlurred(MaintainRecord maintainRecord,User user)
;

public List<MaintainRecord> queryByPagerSix(Pager pager,String actualEndTime)
;

public int countByRemindNo(User user)
;

public List<MaintainRecord> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type)
;

public int countSuccess(User user)
;

}