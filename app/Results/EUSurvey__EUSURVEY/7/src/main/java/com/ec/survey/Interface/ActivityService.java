package com.ec.survey.Interface;
public interface ActivityService {

   public boolean isLogEnabled(int activityCode);
   public void log(int activityCode,String oldValue,String newValue,int userId,String surveyUID);
}