package com.metservice.kanban.model;
 import java.util.Arrays.asList;
import java.util.Collections.unmodifiableCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
public class KanbanBoardRow implements Cloneable,Iterable<KanbanCell>{

 private  KanbanBoardColumnList columns;

 private  KanbanCell[] cells;

public KanbanBoardRow(KanbanBoardColumnList columns) {
    this.columns = columns;
    this.cells = createEmptyCellArray(columns);
}
public KanbanCell getCell(int index){
    return cells[index];
}


public KanbanCell[] createEmptyCellArray(KanbanBoardColumnList columnList){
    KanbanCell[] array = new KanbanCell[columnList.size()];
    int i = 0;
    for (KanbanBoardColumn column : columnList) {
        array[i] = new KanbanCell(column.getWorkItemType());
        i++;
    }
    return array;
}


public void verifyPhasesMatch(KanbanBoardRow otherRow){
    if (otherRow.columns != columns) {
        throw new IllegalArgumentException("rows must contain the same phases");
    }
}


public boolean canAdd(WorkItem workItem){
    String phase = workItem.getCurrentPhase();
    int index = columns.getIndexOfPhase(phase);
    return cells[index].isEmptyCell();
}


public void insert(WorkItem workItem,WorkItem workItemAbove,WorkItem workItemBelow,WorkItem workItemTop){
    String phase = workItem.getCurrentPhase();
    int index = columns.getIndexOfPhase(phase);
    KanbanCell cell = cells[index];
    cell.setWorkItem(workItem);
    cell.setWorkItemAbove(workItemAbove);
    cell.setWorkItemBelow(workItemBelow);
    cell.setWorkItemTop(workItemTop);
}


public KanbanBoardColumnList getColumns(){
    return columns;
}


public boolean hasItemOfType(WorkItemType type){
    for (KanbanCell cell : cells) {
        if (!cell.isEmptyCell() && cell.getWorkItemType().equals(type)) {
            return true;
        }
    }
    return false;
}


@Override
public Iterator<KanbanCell> iterator(){
    return unmodifiableCollection(asList(cells)).iterator();
}


public Iterator<KanbanCell> getIterator(){
    return iterator();
}


public void merge(KanbanBoardRow otherRow){
    verifyPhasesMatch(otherRow);
    for (int i = 0; i < otherRow.cells.length; i++) {
        KanbanCell cell = otherRow.cells[i];
        if (!cell.isEmptyCell()) {
            cells[i] = cell.clone();
        }
    }
}


@Override
public KanbanBoardRow clone(){
    KanbanBoardRow clone = new KanbanBoardRow(columns);
    for (int i = 0; i < cells.length; i++) {
        clone.cells[i] = cells[i].clone();
    }
    return clone;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append('|');
    for (KanbanCell cell : this) {
        builder.append(cell.toFixedWidthString());
        builder.append('|');
    }
    return builder.toString();
}


public Collection<KanbanCell> listOfCellsOfType(WorkItemType type){
    ArrayList<KanbanCell> list = new ArrayList<KanbanCell>();
    for (KanbanCell cell : cells) {
        if (!cell.isEmptyCell() && cell.getWorkItemType().equals(type)) {
            list.add(cell);
        }
    }
    return Collections.unmodifiableCollection(list);
}


}