package com.metservice.kanban.Interface;
public interface WorkItemTree {

   public List<WorkItem> getChildrenWithType(int parentId,WorkItemType childType,String workStream);
}