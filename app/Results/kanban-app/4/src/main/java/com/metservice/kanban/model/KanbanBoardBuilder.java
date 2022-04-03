package com.metservice.kanban.model;
 import com.metservice.kanban.utils.WorkItemUtils;
import com.metservice.kanban.model.WorkItem.ROOT_WORK_ITEM_ID;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.metservice.kanban.Interface.WorkItemTree;
import com.metservice.kanban.Interface.WorkItemTypeCollection;
import com.metservice.kanban.DTO.TreeNode;
public class KanbanBoardBuilder {

 private  KanbanBoardColumnList columns;

 private  WorkItemTree tree;

 private  WorkItemTypeCollection workItemTypes;

/**
 * Default constructor for KanbanBoardBuilder
 * @param columns - columns within the project
 * @param workItemTypes - a collection of all types represented by WorkItems in the project
 * @param tree - a tree representation of all WorkItems in the project
 */
public KanbanBoardBuilder(KanbanBoardColumnList columns, WorkItemTypeCollection workItemTypes, WorkItemTree tree) {
    this.columns = columns;
    this.tree = tree;
    this.workItemTypes = workItemTypes;
}
public KanbanBoard combineHomogenousChildren(int parentId,TreeNode<WorkItemType> childType,String workStream,Comparator<WorkItem> workItemComparator){
    List<WorkItem> workItems = tree.getChildrenWithType(parentId, childType.getValue(), workStream);
    if (childType.hasChildren()) {
        // space rows on the Kanban board for clearer display if there are children in the child board
        return stack(childType, workItems, workStream, workItemComparator);
    } else {
        // pack rows tightly on the Kanban board if there are no children in the child board
        return pack(workItems, workItemComparator);
    }
}


public KanbanBoard stack(TreeNode<WorkItemType> type,List<WorkItem> workItems,String workStream,Comparator<WorkItem> workItemComparator){
    KanbanBoard board = new KanbanBoard(columns);
    List<WorkItem> list = columns.filter(workItems, workItemComparator);
    WorkItem workItemTop = WorkItemUtils.topWorkItem(list);
    for (int i = 0; i < list.size(); i++) {
        WorkItem workItem = list.get(i);
        WorkItem workItemBefore = (i - 1) >= 0 ? list.get(i - 1) : null;
        WorkItem workItemAfter = (i + 1) < list.size() ? list.get(i + 1) : null;
        // build the corresponding child board format it
        KanbanBoard childBoard = build(type, workItem, workItemBefore, workItemAfter, workStream, workItemTop);
        board.stack(childBoard);
    }
    return board;
}


public KanbanBoard build(TreeNode<WorkItemType> workItemTypeTreeNode,WorkItem workItem,WorkItem workItemBefore,WorkItem workItemAfter,String workStream,WorkItem workItemTop){
    KanbanBoard board = new KanbanBoard(columns);
    if (isVisible(workItem)) {
        board.insert(workItem, workItemBefore, workItemAfter, workItemTop);
        // build child boards and merge them with the main Kanban board
        for (TreeNode<WorkItemType> childType : workItemTypeTreeNode.getChildren()) {
            // combine child boards depending on the type of the WorkItem
            KanbanBoard childBoard = combineHomogenousChildren(workItem.getId(), childType, workStream, null);
            board.merge(childBoard);
        }
    }
    return board;
}


public KanbanBacklog buildKanbanBacklog(String workStream){
    List<WorkItem> workItems = tree.getChildrenWithType(ROOT_WORK_ITEM_ID, workItemTypes.getRoot().getValue(), workStream);
    List<KanbanCell> cells = new ArrayList<KanbanCell>();
    List<WorkItem> list = columns.filter(workItems);
    WorkItem workItemTop = WorkItemUtils.topWorkItem(list);
    // create cell for each workitem
    for (int i = 0; i < list.size(); i++) {
        WorkItem workItem = list.get(i);
        if (workItem.isInWorkStream(workStream)) {
            WorkItem workItemBefore = (i - 1) >= 0 ? list.get(i - 1) : null;
            WorkItem workItemAfter = (i + 1) < list.size() ? list.get(i + 1) : null;
            KanbanCell cell = new KanbanCell(workItem.getType());
            cell.setWorkItem(workItem);
            cell.setWorkItemAbove(workItemBefore);
            cell.setWorkItemBelow(workItemAfter);
            cell.setWorkItemTop(workItemTop);
            cells.add(cell);
        }
    }
    return new KanbanBacklog(cells);
}


public boolean isVisible(WorkItem workItem){
    return columns.containsPhase(workItem.getCurrentPhase());
}


public KanbanBoard pack(List<WorkItem> workItems,Comparator<WorkItem> workItemComparator){
    KanbanBoard board = new KanbanBoard(columns);
    for (KanbanBoardColumn column : columns) {
        List<WorkItem> sublist = new KanbanBoardColumnList(column).filter(workItems, workItemComparator);
        WorkItem workItemTop = WorkItemUtils.topWorkItem(sublist);
        for (int i = 0; i < sublist.size(); i++) {
            WorkItem workItem = sublist.get(i);
            WorkItem workItemBefore = (i - 1) >= 0 ? sublist.get(i - 1) : null;
            WorkItem workItemAfter = (i + 1) < sublist.size() ? sublist.get(i + 1) : null;
            // insert the WorkItem into the appropriate position in the list
            board.insert(workItem, workItemBefore, workItemAfter, workItemTop);
        }
    }
    return board;
}


}