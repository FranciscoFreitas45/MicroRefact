package com.metservice.kanban.model;
 import java.lang.String.format;
import org.apache.commons.lang.UnhandledException;
import com.metservice.kanban.Interface.WorkItemType;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
public class KanbanCell implements Cloneable{

 private  WorkItemType workItemType;

 private  WorkItem workItem;

 private  WorkItem workItemAbove;

 private  WorkItem workItemBelow;

 private  WorkItem workItemTop;

public KanbanCell(WorkItemType workItemType) {
    this.workItemType = workItemType;
}
public void setWorkItemAbove(WorkItem workItemAbove){
    this.workItemAbove = workItemAbove;
}


public void setWorkItem(WorkItem workItem){
    if (!isEmptyCell()) {
        throw new IllegalArgumentException("work item already exists in this position");
    }
    this.workItem = workItem;
}


public void setWorkItemBelow(WorkItem workItemBelow){
    this.workItemBelow = workItemBelow;
}


public WorkItem getWorkItem(){
    return workItem;
}


public String toFixedWidthString(){
    return isEmptyCell() ? "   " : format("%3d", workItem.getId());
}


public WorkItem getWorkItemTop(){
    return workItemTop;
}


public boolean isEmptyCell(){
    return workItem == null;
}


@Override
public KanbanCell clone(){
    try {
        return (KanbanCell) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new UnhandledException(e);
    }
}


public void setWorkItemTop(WorkItem workItemTop){
    this.workItemTop = workItemTop;
}


@Override
public String toString(){
    return isEmptyCell() ? "empty cell" : workItem.toString();
}


public WorkItem getWorkItemBelow(){
    return workItemBelow;
}


public WorkItemType getWorkItemType(){
    return workItemType;
}


public WorkItem getWorkItemAbove(){
    return workItemAbove;
}


}