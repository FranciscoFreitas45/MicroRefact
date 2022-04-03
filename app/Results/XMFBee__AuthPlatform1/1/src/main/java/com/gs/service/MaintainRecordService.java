package com.gs.service;
 import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import java.util.List;
public interface MaintainRecordService extends BaseService<String, MaintainRecord>{


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

public List<MaintainRecord> queryByPagerSix(Pager pager,String actualEndTime)
;

public int countByRemindNo(User user)
;

public List<MaintainRecord> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type)
;

public int countSuccess(User user)
;

}