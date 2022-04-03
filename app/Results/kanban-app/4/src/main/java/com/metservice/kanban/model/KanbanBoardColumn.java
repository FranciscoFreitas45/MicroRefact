package com.metservice.kanban.model;
 import com.metservice.kanban.Interface.WorkItemType;
public class KanbanBoardColumn {

 private  WorkItemType type;

 private  String phase;

 private  int wipLimit;

public KanbanBoardColumn(WorkItemType type, String phase) {
    this.type = type;
    this.phase = phase;
    this.wipLimit = -1;
}public KanbanBoardColumn(WorkItemType type, String phase, int wipLimit) {
    this.type = type;
    this.phase = phase;
    this.wipLimit = wipLimit;
}
public int getWIPLimit(){
    return wipLimit;
}


public void setWIPLimit(int columnLimit){
    this.wipLimit = columnLimit;
}


@Override
public String toString(){
    return type + "/" + phase;
}


public WorkItemType getWorkItemType(){
    return type;
}


public String getPhase(){
    return phase;
}


}