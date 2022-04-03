package com.gs.Interface;
public interface MaterialUseService {

   public int countNoUseRecord(String companyId);
   public List<RecordBaseView> queryNoUseRecord(String companyId,Pager pager);
   public int countHasUseRecord(String companyId);
   public List<RecordBaseView> queryHasUseRecord(String companyId,Pager pager);
   public List<WorkInfo> userWorksStatusByPager(User user,String status,Pager pager);
   public int countUserWorksStatus(User user,String status);
   public List<DetailTemp> queryDetailsByRecordId(String recordId,String companyId,Pager pager);
   public int countDetailsByRecordId(String recordId,String companyId);
   public List<User> companyEmps(String companyId);
   public boolean recordIsDisp(String recordId);
   public int insertWorkInfo(WorkInfo workInfo);
   public String queryUserIdbyRecordId4workInfo(String recordId);
   public int updRunProInstStartUser(String newUserId,String recordId,String flowName);
   public int updWorkInfoUser(String recordId,String userId);
}