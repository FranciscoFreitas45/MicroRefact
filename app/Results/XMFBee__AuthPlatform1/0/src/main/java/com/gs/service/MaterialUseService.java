package com.gs.service;
 import com.gs.bean;
import com.gs.bean.view;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface MaterialUseService extends BaseService<String, MaterialUse>{


public List<RecordBaseView> queryHasUseRecord(String companyId,Pager pager)
;

public int countReturnFlowing(String flowName,User user,String roleId)
;

public List<MaterialURTemp> userHistByPager(Pager pager,String userId)
;

public int countUserHist(String userId)
;

public List<WorkInfo> userWorksByPager(User user,Pager pager)
;

public int countDetailsByRecordId(String recordId,String companyId)
;

public int updRunProInstStartUser(String newUserId,String recordId,String flowName)
;

public List accQueryAll(User user)
;

public List<MaterialURTemp> histByPager(Pager pager)
;

public int insertWorkInfo(WorkInfo workInfo)
;

public List<DetailTemp> queryDetailsByRecordId(String recordId,String companyId,Pager pager)
;

public int updWorkInfoUser(String recordId,String userId)
;

public List<MaterialURTemp> queryUseFlowingbyPager(String flowName,User user,String roleId,Pager pager)
;

public int countHist()
;

public List queryHistoryFlowingbyPager(User user,String flowName,String taskKey,Pager pager)
;

public List<MaterialUse> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

public int countUserFlowing(String flowName,User user)
;

public List<User> companyEmps(String companyId)
;

public int countUserWorks(User user)
;

public Accessories accQueryById(String accId)
;

public List queryUserFlowingByPager(String flowName,User user,String reviewTaskName,Pager pager)
;

public List<RecordBaseView> queryNoUseRecord(String companyId,Pager pager)
;

public List<Company> queryNearCompanys(Map pointsMap)
;

public int countHistoryFlowing(User user,String flowName)
;

public int countHasUseRecord(String companyId)
;

public int countUserWorksStatus(User user,String status)
;

public int countNoUseRecord(String companyId)
;

public List<WorkInfo> userWorksStatusByPager(User user,String status,Pager pager)
;

public String queryUserIdbyRecordId4workInfo(String recordId)
;

public boolean recordIsDisp(String recordId)
;

public int countUseFlowing(String flowName,User user,String roleId)
;

public List<MaterialURTemp> queryReturnFlowingbyPager(String flowName,User user,String roleId,Pager pager)
;

}