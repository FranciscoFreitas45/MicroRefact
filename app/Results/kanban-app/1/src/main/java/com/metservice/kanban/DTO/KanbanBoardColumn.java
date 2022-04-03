package com.metservice.kanban.DTO;
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


public WorkItemType getWorkItemType(){
    return type;
}


public String getPhase(){
    return phase;
}


}