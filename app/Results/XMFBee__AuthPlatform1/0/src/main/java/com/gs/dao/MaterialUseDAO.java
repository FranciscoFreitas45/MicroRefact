package com.gs.dao;
 import com.gs.bean;
import com.gs.bean.view;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface MaterialUseDAO extends BaseDAO<String, MaterialUse>{


public int countMaterials(String userId)
;

public int countUserFlowing(Map hashMap)
;

public List<User> companyEmps(String companyId)
;

public int countUserWorks(User user,String status)
;

public Accessories accQueryById(String accId)
;

public List<WorkInfo> userWorksByPager(User user,String status,Pager pager)
;

public List queryUserFlowingByPager(Map hashMap)
;

public List<RecordBaseView> queryCurRecordsByPager(String companyId,String hasEmp,Pager pager)
;

public int countDetailsByRecordId(String recordId,String companyId)
;

public int updRunProInstStartUser(String newUserId,String recordId,String flowName)
;

public List<Company> queryNearCompanys(Map pointsMap)
;

public int countHistoryFlowing(Map hashMap)
;

public List accQueryAll(User user)
;

public List<MaterialURTemp> queryMaterialFlowingbyPager(Map hashMap)
;

public int countMaterialFlowing(Map hashMap)
;

public int countNoUseRecord(String companyId,String hasEmp)
;

public int insertWorkInfo(WorkInfo workInfo)
;

public List<DetailTemp> queryDetailsByRecordId(String recordId,String companyId,Pager pager)
;

public String queryUserIdbyRecordId4workInfo(String recordId)
;

public int updWorkInfoUser(String recordId,String userId)
;

public List<MaterialURTemp> materialByPager(Pager pager,String userId)
;

public WorkInfo queryWorkInfoByRecordId(String recordId)
;

public List queryHistoryFlowingbyPager(Map hashMap)
;

public List<MaterialUse> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

}