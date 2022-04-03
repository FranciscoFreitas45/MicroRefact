package com.Interface;
public interface ReportService {

   public Map<String,Object> getPersonCreditReport(String login_name);
   public boolean parseAndSave(String content,String loginName);
}