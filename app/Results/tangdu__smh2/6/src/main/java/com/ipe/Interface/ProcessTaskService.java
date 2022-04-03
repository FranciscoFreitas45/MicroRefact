package com.ipe.Interface;
public interface ProcessTaskService {

   public BodyWrapper userTaskList(String params,RestRequest rest);
   public BodyWrapper getTaskList(String params,RestRequest rest);
   public void delTask(String taskId);
   public void clainTask(String taskId);
   public void releaseTask(String taskId);
   public void taskProxy(String taskId,String userId);
   public void taskDelegate(String taskId,String userId);
}