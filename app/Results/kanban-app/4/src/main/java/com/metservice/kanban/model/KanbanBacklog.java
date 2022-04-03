package com.metservice.kanban.model;
 import java.util.Arrays.asList;
import java.util.Collections.unmodifiableList;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
public class KanbanBacklog extends AbstractList<KanbanCell>{

 private  List<KanbanCell> cells;

public KanbanBacklog(KanbanCell... cells) {
    this(asList(cells));
}public KanbanBacklog(List<KanbanCell> cells) {
    this.cells = unmodifiableList(new ArrayList<KanbanCell>(cells));
}
@Override
public int size(){
    return cells.size();
}


@Override
public KanbanCell get(int index){
    return cells.get(index);
}


}