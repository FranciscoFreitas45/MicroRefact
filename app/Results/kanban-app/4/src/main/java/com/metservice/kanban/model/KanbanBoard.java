package com.metservice.kanban.model;
 import java.util.Collections.unmodifiableCollection;
import org.apache.commons.lang.SystemUtils.LINE_SEPARATOR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class KanbanBoard implements Iterable<KanbanBoardRow>{

 private  KanbanBoardColumnList columns;

 private  List<KanbanBoardRow> rows;

 private  Map<String,Integer> itemsInColumn;

public KanbanBoard(KanbanBoardColumnList columns) {
    this.columns = columns;
}
public void stack(KanbanBoard otherBoard){
    for (KanbanBoardRow row : otherBoard) {
        rows.add(row.clone());
    }
}


@Override
public Iterator<KanbanBoardRow> iterator(){
    return unmodifiableCollection(rows).iterator();
}


public Iterator<KanbanBoardRow> getIterator(){
    return iterator();
}


public void extendLength(int newLength){
    for (int i = rows.size(); i < newLength; i++) {
        KanbanBoardRow blankRow = new KanbanBoardRow(columns);
        rows.add(blankRow);
    }
}


public KanbanCell getCell(int rowIndex,int columnIndex){
    return rows.get(rowIndex).getCell(columnIndex);
}


public Map<String,Integer> getItemsInColumn(){
    if (itemsInColumn == null) {
        itemsInColumn = computeItemsInColumn();
    }
    return itemsInColumn;
}


public void merge(KanbanBoard otherBoard){
    extendLength(otherBoard.rows.size());
    mergeRows(otherBoard);
}


public void mergeRows(KanbanBoard otherBoard){
    for (int i = 0; i < otherBoard.rows.size(); i++) {
        KanbanBoardRow destination = rows.get(i);
        KanbanBoardRow source = otherBoard.rows.get(i);
        destination.merge(source);
    }
}


public Map<String,Integer> computeItemsInColumn(){
    Map<String, Integer> result = new HashMap<String, Integer>();
    for (KanbanBoardRow row : rows) {
        int column = 0;
        for (KanbanCell cell : row) {
            if (!cell.isEmptyCell()) {
                String columnName = row.getColumns().get(column).getPhase();
                int previous = 0;
                if (result.containsKey(columnName)) {
                    previous = result.get(columnName);
                }
                result.put(columnName, previous + 1);
            }
            column++;
        }
    }
    return result;
}


public void insert(WorkItem workItem,WorkItem workItemAbove,WorkItem workItemBelow,WorkItem workItemTop){
    KanbanBoardRow targetRow = null;
    for (KanbanBoardRow row : this) {
        if (row.canAdd(workItem)) {
            targetRow = row;
            break;
        }
    }
    if (targetRow == null) {
        // no space: add a new row
        targetRow = new KanbanBoardRow(columns);
        rows.add(targetRow);
    }
    targetRow.insert(workItem, workItemAbove, workItemBelow, workItemTop);
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    for (KanbanBoardRow row : this) {
        builder.append(row);
        builder.append(LINE_SEPARATOR);
    }
    return builder.toString();
}


}