package com.metservice.kanban.model;
 import java.util.HashMap;
import java.util.Map;
public class KanbanBoardConfiguration {

 private  Map<BoardIdentifier,KanbanBoardColumnList> columnsByBoard;


public void add(BoardIdentifier boardType,KanbanBoardColumnList columns){
    columnsByBoard.put(boardType, columns);
}


public KanbanBoardColumnList get(BoardIdentifier boardType){
    return columnsByBoard.get(boardType);
}


}