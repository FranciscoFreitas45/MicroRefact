package com.ec.survey.Interface;
public interface ReportingServiceProxy {

   public int getCount(Survey survey,String quid,String auid,boolean noPrefixSearch,boolean noPostfixSearch,String where,Map<String,Object> values);
   public boolean OLAPTableExists(String uid,boolean draft);
}