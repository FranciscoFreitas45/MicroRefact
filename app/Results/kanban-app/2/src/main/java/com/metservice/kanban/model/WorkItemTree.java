package com.metservice.kanban.model;
 import java.util.Collection;
import java.util.List;
import java.util.Map;
public interface WorkItemTree {


public WorkItem getParent(WorkItem workItem)
;

public void move(WorkItem workItem,WorkItem target,boolean after)
;

public Map<Integer,WorkItem> getWorkItemsById()
;

public List<WorkItem> getWorkItemsOfType(WorkItemType type,String workStream)
;

public void reorder(WorkItem workItem,List<WorkItem> workItemList)
;

public void delete(int id)
;

public Collection<WorkItem> getParentAlternatives(WorkItem workItem)
;

public List<WorkItem> getChildren(int parentId)
;

public WorkItem getTopLevelAncestor(WorkItem workItem)
;

public WorkItem getWorkItem(int id)
;

public void addWorkItem(WorkItem workItem)
;

public void reparent(int id,int newParentId)
;

public List<WorkItem> getWorkItemList()
;

public List<WorkItem> getChildrenWithType(int parentId,WorkItemType childType,String workStream)
;

public int getNewId()
;

public void addWorkItems(WorkItem workItem)
;

}