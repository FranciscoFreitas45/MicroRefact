package com.gs.service.impl;
 import com.gs.bean;
import com.gs.bean.view;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialUseDAO;
import com.gs.service.MaterialUseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MaterialUseServiceImpl implements MaterialUseService{

@Resource
 private  MaterialUseDAO materialUseDAO;

 private  String flag;

 private  String id;


public List<MaterialUse> queryByPagerDisable(Pager pager){
    return materialUseDAO.queryByPagerDisable(pager);
}


@Override
public List<MaterialURTemp> userHistByPager(Pager pager,String userId){
    return materialUseDAO.materialByPager(pager, userId);
}


@Override
public int countUserHist(String userId){
    return materialUseDAO.countMaterials(userId);
}


@Override
public int countDetailsByRecordId(String recordId,String companyId){
    return materialUseDAO.countDetailsByRecordId(recordId, companyId);
}


@Override
public int updRunProInstStartUser(String newUserId,String recordId,String flowName){
    return materialUseDAO.updRunProInstStartUser(newUserId, recordId, flowName);
}


@Override
public List accQueryAll(User user){
    return materialUseDAO.accQueryAll(user);
}


@Override
public List<MaterialURTemp> histByPager(Pager pager){
    return materialUseDAO.materialByPager(pager);
}


public int inactive(String id){
    return materialUseDAO.inactive(id);
}


public int countByBlurred(MaterialUse materialUse){
    return 0;
}


@Override
public List<DetailTemp> queryDetailsByRecordId(String recordId,String companyId,Pager pager){
    return materialUseDAO.queryDetailsByRecordId(recordId, companyId, pager);
}


@Override
public List<MaterialURTemp> queryUseFlowingbyPager(String flowName,User user,String curActId,Pager pager){
    Map paramMap = new HashMap();
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    paramMap.put("curActId", curActId);
    paramMap.put("isUse", true);
    paramMap.put("pager", pager);
    return materialUseDAO.queryMaterialFlowingbyPager(paramMap);
}


public void setId(String id){
    this.id = id;
}


public String getFlag(){
    return flag;
}


public void setFlag(String flag){
    this.flag = flag;
}


@Override
public int countUserWorks(User user){
    return materialUseDAO.countUserWorks(user);
}


public MaterialUse query(MaterialUse materialUse){
    return materialUseDAO.query(materialUse);
}


public int count(User user){
    return materialUseDAO.count(user);
}


public int active(String id){
    return materialUseDAO.active(id);
}


@Override
public int countHasUseRecord(String companyId){
    return materialUseDAO.countNoUseRecord(companyId, "Y");
}


@Override
public String queryUserIdbyRecordId4workInfo(String recordId){
    return materialUseDAO.queryUserIdbyRecordId4workInfo(recordId);
}


public int deleteById(String id){
    return materialUseDAO.deleteById(id);
}


@Override
public boolean recordIsDisp(String recordId){
    WorkInfo workInfo = materialUseDAO.queryWorkInfoByRecordId(recordId);
    if (workInfo != null)
        return true;
    return false;
}


@Override
public int countUseFlowing(String flowName,User user,String curActId){
    Map paramMap = new HashMap();
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    paramMap.put("curActId", curActId);
    paramMap.put("isUse", true);
    return materialUseDAO.countMaterialFlowing(paramMap);
}


@Override
public List<RecordBaseView> queryHasUseRecord(String companyId,Pager pager){
    return materialUseDAO.queryCurRecordsByPager(companyId, "Y", pager);
}


@Override
public int countReturnFlowing(String flowName,User user,String roleId){
    Map paramMap = new HashMap();
    paramMap.put("roleId", roleId);
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    paramMap.put("isUse", false);
    return materialUseDAO.countMaterialFlowing(paramMap);
}


public int batchInsert(List<MaterialUse> list){
    return materialUseDAO.batchInsert(list);
}


public int batchUpdate(List<MaterialUse> list){
    return materialUseDAO.batchUpdate(list);
}


@Override
public List<WorkInfo> userWorksByPager(User user,Pager pager){
    return materialUseDAO.userWorksByPager(user, pager);
}


public int insert(MaterialUse materialUse){
    return materialUseDAO.insert(materialUse);
}


public int update(MaterialUse materialUse){
    return materialUseDAO.update(materialUse);
}


public String getId(){
    return id;
}


@Override
public List<MaterialUse> queryAll(String status){
    return materialUseDAO.queryAll(status);
}


public int delete(MaterialUse materialUse){
    return materialUseDAO.delete(materialUse);
}


public int batchDelete(List<MaterialUse> list){
    return materialUseDAO.batchDelete(list);
}


@Override
public int insertWorkInfo(WorkInfo workInfo){
    return materialUseDAO.insertWorkInfo(workInfo);
}


@Override
public int updWorkInfoUser(String recordId,String userId){
    return materialUseDAO.updWorkInfoUser(recordId, userId);
}


@Override
public int countHist(){
    return materialUseDAO.countMaterials();
}


@Override
public List queryHistoryFlowingbyPager(User user,String flowName,String taskKey,Pager pager){
    Map paramMap = new HashMap();
    paramMap.put("user", user);
    paramMap.put("flowName", flowName);
    paramMap.put("taskKey", taskKey);
    paramMap.put("pager", pager);
    return materialUseDAO.queryHistoryFlowingbyPager(paramMap);
}


@Override
public List<MaterialUse> queryByCondition(String start,String end,String companyId,String accTypeId,String type){
    return materialUseDAO.queryByCondition(start, end, companyId, accTypeId, type);
}


@Override
public int countUserFlowing(String flowName,User user){
    Map paramMap = new HashMap();
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    return materialUseDAO.countUserFlowing(paramMap);
}


@Override
public List<User> companyEmps(String companyId){
    return materialUseDAO.companyEmps(companyId);
}


@Override
public Accessories accQueryById(String accId){
    return materialUseDAO.accQueryById(accId);
}


@Override
public List queryUserFlowingByPager(String flowName,User user,String reviewTaskName,Pager pager){
    Map paramMap = new HashMap();
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    paramMap.put("reviewTaskName", reviewTaskName);
    paramMap.put("pager", pager);
    return materialUseDAO.queryUserFlowingByPager(paramMap);
}


public List<MaterialUse> blurredQuery(Pager pager,MaterialUse materialUse){
    return null;
}


@Override
public List<RecordBaseView> queryNoUseRecord(String companyId,Pager pager){
    return materialUseDAO.queryCurRecordsByPager(companyId, "", pager);
}


@Override
public List<Company> queryNearCompanys(Map pointsMap){
    return materialUseDAO.queryNearCompanys(pointsMap);
}


@Override
public int countHistoryFlowing(User user,String flowName){
    Map paramMap = new HashMap();
    paramMap.put("user", user);
    paramMap.put("flowName", flowName);
    return materialUseDAO.countHistoryFlowing(paramMap);
}


public List<MaterialUse> queryByStatus(String status){
    return materialUseDAO.queryAll(status);
}


@Override
public int countUserWorksStatus(User user,String status){
    return materialUseDAO.countUserWorks(user, status);
}


@Override
public int countNoUseRecord(String companyId){
    return materialUseDAO.countNoUseRecord(companyId, "");
}


@Override
public List<WorkInfo> userWorksStatusByPager(User user,String status,Pager pager){
    return materialUseDAO.userWorksByPager(user, status, pager);
}


public int countByDisable(User user){
    return materialUseDAO.countByDisable(user);
}


public MaterialUse queryById(String id){
    return materialUseDAO.queryById(id);
}


public List<MaterialUse> queryByPager(Pager pager){
    return materialUseDAO.queryByPager(pager);
}


@Override
public List<MaterialURTemp> queryReturnFlowingbyPager(String flowName,User user,String curActId,Pager pager){
    Map paramMap = new HashMap();
    paramMap.put("flowName", flowName);
    paramMap.put("user", user);
    paramMap.put("curActId", curActId);
    paramMap.put("isUse", false);
    paramMap.put("pager", pager);
    return materialUseDAO.queryMaterialFlowingbyPager(paramMap);
}


}