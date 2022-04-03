package com.Interface;
public interface Check_RecordService {

   public List<Check_Record> findCreditCardApproval(Integer reportId);
   public List<Check_Record> findPersonageQuery(Integer reportId);
   public List<Check_Record> findLoanAfterManager(Integer reportId);
   public List<Check_Record> findLoanApproval(Integer reportId);
}