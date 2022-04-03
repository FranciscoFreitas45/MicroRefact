package com.metservice.kanban.model;
 import com.metservice.kanban.model.WorkItem.ROOT_WORK_ITEM_ID;
import java.lang.Math.max;
import java.util.Collections.emptyList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DefaultWorkItemTree implements WorkItemTree{

 private  Map<Integer,WorkItem> workItemsById;

 private  Map<Integer,List<WorkItem>> workItemsByParentId;


@Override
public WorkItem getParent(WorkItem workItem){
    return getWorkItem(workItem.getParentId());
}


public void addDescendentsToList(int id,List<WorkItem> list){
    List<WorkItem> children = getChildren(id);
    for (WorkItem child : children) {
        list.add(child);
        addDescendentsToList(child.getId(), list);
    }
}


@Override
public void move(WorkItem workItem,WorkItem target,boolean after){
    List<WorkItem> siblings = workItemsByParentId.get(workItem.getParentId());
    siblings.remove(workItem);
    int pos = siblings.indexOf(target) + (after ? 1 : 0);
    siblings.add(pos < 0 ? siblings.size() : pos, workItem);
}


@Override
public Map<Integer,WorkItem> getWorkItemsById(){
    return workItemsById;
}


@Override
public List<WorkItem> getWorkItemsOfType(WorkItemType type,String workStream){
    Collection<WorkItem> workItems = new ArrayList<WorkItem>();
    for (WorkItem workItem : getWorkItemList()) {
        if (workItem.getType().equals(type) && workItem.isInWorkStream(workStream)) {
            workItems.add(workItem);
        }
    }
    return new ArrayList<WorkItem>(workItems);
}


@Override
public void reorder(WorkItem workItem,List<WorkItem> workItemList){
    verifyReorder(workItem, workItemList);
    int pos = workItemList.indexOf(workItem);
    move(workItem, pos == 0 ? null : workItemList.get(pos - 1), true);
}


public void verifyReorder(WorkItem workItem,List<WorkItem> workItemList){
    List<WorkItem> currentList = filteredList(workItem, workItemsByParentId.get(workItem.getParentId()));
    List<WorkItem> newList = filteredList(workItem, workItemList);
    if (!Arrays.equals(currentList.toArray(), newList.toArray())) {
        throw new IllegalStateException("Tree was changed by someone else.\nCurrent\n" + currentList + "\nNew\n" + newList);
    }
}


@Override
public void delete(int id){
    if (!workItemsById.containsKey(id)) {
        throw new IllegalArgumentException("unknown work item id: " + id);
    }
    if (!getChildren(id).isEmpty()) {
        throw new IllegalStateException("cannot delete work item with children");
    }
    WorkItem item = getWorkItem(id);
    workItemsById.remove(id);
    workItemsByParentId.get(item.getParentId()).remove(item);
}


@Override
public Collection<WorkItem> getParentAlternatives(WorkItem workItem){
    return workItem.getParentId() == ROOT_WORK_ITEM_ID ? Collections.<WorkItem>singleton(getRoot()) : getChildren(getParent(workItem).getParentId());
}


@Override
public List<WorkItem> getChildren(int parentId){
    List<WorkItem> workItems;
    if (workItemsByParentId.containsKey(parentId)) {
        workItems = workItemsByParentId.get(parentId);
    } else {
        workItems = emptyList();
    }
    return new ArrayList<WorkItem>(workItems);
}


@Override
public WorkItem getTopLevelAncestor(WorkItem workItem){
    int parentId = workItem.getParentId();
    if (parentId == ROOT_WORK_ITEM_ID) {
        return workItem;
    }
    return getTopLevelAncestor(getWorkItem(parentId));
}


@Override
public WorkItem getWorkItem(int id){
    return workItemsById.get(id);
}


@Override
public void addWorkItem(WorkItem workItem){
    workItemsById.put(workItem.getId(), workItem);
    indexByParentId(workItem, workItem.getParentId());
}


public List<WorkItem> filteredList(WorkItem workItem,List<WorkItem> workItemList){
    List<WorkItem> newList = new ArrayList<WorkItem>();
    for (WorkItem item : workItemList) {
        if (item.getCurrentPhase() == workItem.getCurrentPhase() && item != workItem) {
            newList.add(item);
        }
    }
    return newList;
}


@Override
public void reparent(int id,int newParentId){
    WorkItem oldWorkItem = workItemsById.remove(id);
    List<WorkItem> siblings = workItemsByParentId.get(oldWorkItem.getParentId());
    siblings.remove(oldWorkItem);
    addWorkItem(oldWorkItem.withNewParent(newParentId));
}


@Override
public List<WorkItem> getWorkItemList(){
    List<WorkItem> workItems = new ArrayList<WorkItem>();
    addDescendentsToList(ROOT_WORK_ITEM_ID, workItems);
    return workItems;
}


public WorkItem getRoot(){
    WorkItem root = new WorkItem(0, -1, null);
    root.setName("Top level");
    return root;
}


@Override
public List<WorkItem> getChildrenWithType(int parentId,WorkItemType childType,String workStream){
    List<WorkItem> workItems = new ArrayList<WorkItem>();
    for (WorkItem workItem : getChildren(parentId)) {
        if (workItem.getType().equals(childType) && workItem.isInWorkStream(workStream)) {
            workItems.add(workItem);
        }
    }
    return new ArrayList<WorkItem>(workItems);
}


@Override
public int getNewId(){
    int largestIdInUse = ROOT_WORK_ITEM_ID;
    for (WorkItem workItem : getWorkItemList()) {
        largestIdInUse = max(largestIdInUse, workItem.getId());
    }
    return largestIdInUse + 1;
}


public void indexByParentId(WorkItem workItem,int parentId){
    if (!workItemsByParentId.containsKey(parentId)) {
        workItemsByParentId.put(parentId, new ArrayList<WorkItem>());
    }
    workItemsByParentId.get(parentId).add(workItem);
}


public void addWorkItems(WorkItem workItems){
    for (WorkItem workItem : workItems) {
        addWorkItem(workItem);
    }
}


}