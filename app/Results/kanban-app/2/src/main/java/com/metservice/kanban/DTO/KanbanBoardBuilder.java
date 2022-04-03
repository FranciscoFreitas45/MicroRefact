package com.metservice.kanban.DTO;
 import com.metservice.kanban.utils.WorkItemUtils;
import com.metservice.kanban.model.WorkItem.ROOT_WORK_ITEM_ID;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.metservice.kanban.Interface.WorkItemTree;
import com.metservice.kanban.Interface.WorkItemTypeCollection;
public class KanbanBoardBuilder {

 private  KanbanBoardColumnList columns;

 private  WorkItemTree tree;

 private  WorkItemTypeCollection workItemTypes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

.queryParam("workItemTypeTreeNode",workItemTypeTreeNode)
.queryParam("workItem",workItem)
.queryParam("workItemBefore",workItemBefore)
.queryParam("workItemAfter",workItemAfter)
.queryParam("workStream",workStream)
.queryParam("workItemTop",workItemTop)
;
KanbanBoard aux = restTemplate.getForObject(builder.toUriString(),KanbanBoard.class);
return aux;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildKanbanBacklog"))

.queryParam("workStream",workStream)
;
KanbanBacklog aux = restTemplate.getForObject(builder.toUriString(),KanbanBacklog.class);
return aux;
}


}