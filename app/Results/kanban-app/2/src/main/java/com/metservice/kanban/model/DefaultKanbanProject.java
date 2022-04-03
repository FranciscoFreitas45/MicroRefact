package com.metservice.kanban.model;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.NotImplementedException;
import org.joda.time.LocalDate;
import com.metservice.kanban.charts.ChartUtils;
import com.metservice.kanban.charts.burnup.BurnUpDataModel;
import com.metservice.kanban.charts.burnup.ProjectedDatasetPopulator;
import com.metservice.kanban.web.KanbanPersistence;
import com.metservice.kanban.DTO.KanbanBoardBuilder;
import com.metservice.kanban.DTO.ProjectedDatasetPopulator;
public class DefaultKanbanProject implements KanbanProject{

 private  WorkItemTypeCollection workItemTypes;

 private  KanbanBoardConfiguration columnsByBoard;

 private  WorkItemTree tree;

 private  KanbanPersistence persistence;

 private  String name;

/**
 * Default constructor for DefaultKanbanProject.
 * @param workItemTypes - the collection of all WorkItemTypes represented in the tree of workItems.
 * @param phaseSequences - the representation of project phases on a board
 * @param tree - the collection of all WorkItems in the project
 * @param persistence - the raw file containing the data in the project
 */
public DefaultKanbanProject(WorkItemTypeCollection workItemTypes, KanbanBoardConfiguration phaseSequences, WorkItemTree tree, KanbanPersistence persistence, String name) {
    this.workItemTypes = workItemTypes;
    this.columnsByBoard = phaseSequences;
    this.tree = tree;
    this.persistence = persistence;
    this.name = name;
}
@Override
public LocalDate getStartDate(){
    return ChartUtils.getFirstDate(tree.getWorkItemList());
}


@Override
public WorkItemType getChildType(WorkItemType parentType){
    return getWorkItemTypes().getTreeNode(parentType).getChild(0).getValue();
}


@Override
public void reparentWorkItem(int id,int newParentId){
    tree.reparent(id, newParentId);
}


public String getName(){
    return name;
}


@Override
public KanbanBoard getBoard(BoardIdentifier boardType,String workStream){
    KanbanBoardBuilder kanbanBoardBuilder = new KanbanBoardBuilder(columnsByBoard.get(boardType), workItemTypes, tree);
    return kanbanBoardBuilder.build(workStream, null);
}


@Override
public void save(){
    persistence.write(tree);
}


@Override
public void deleteJournalItem(int itemId){
    try {
        List<KanbanJournalItem> journal = persistence.journalRead();
        for (KanbanJournalItem item : journal) {
            if (item.getId() == itemId) {
                journal.remove(item);
                break;
            }
        }
        persistence.journalWrite(journal);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@Override
public void reorder(Integer id,Integer[] newIdList){
    List<WorkItem> list = new ArrayList<WorkItem>();
    for (Integer i : newIdList) {
        list.add(tree.getWorkItem(i));
    }
    tree.reorder(tree.getWorkItem(id), list);
}


public WorkItemType getRootWorkItemType(){
    return workItemTypes.getRoot().getValue();
}


@Override
public WorkItemTypeCollection getWorkItemTypes(){
    return workItemTypes;
}


@Override
public Set<String> getWorkStreams(){
    // TODO: for large projects this can be inefficient - consider caching this set
    Set<String> workStreams = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
    for (WorkItem workItems : tree.getWorkItemList()) {
        for (String ws : workItems.getWorkStreams()) {
            workStreams.add(ws);
        }
    }
    return workStreams;
}


@Override
public void advance(int id,LocalDate date){
    tree.getWorkItem(id).advance(date);
}


@Override
public KanbanBoardColumnList getColumns(BoardIdentifier boardType){
    return columnsByBoard.get(boardType);
}


@Override
public void updateJournalItem(KanbanJournalItem journalItem){
    throw new NotImplementedException();
}


@Override
public KanbanBoardColumnList getWallColumns(){
    return getColumns(BoardIdentifier.WALL);
}


@Override
public KanbanBoard getCompleted(String workStream){
    // get last column
    KanbanBoardBuilder kanbanBoardBuilder = new KanbanBoardBuilder(new KanbanBoardColumnList(new KanbanBoardColumn(getRootWorkItemType(), getRootWorkItemType().getCompletedPhase())), workItemTypes, tree);
    return kanbanBoardBuilder.build(workStream, WorkItem.LAST_PHASE_DATE_COMPARATOR);
}


@Override
public KanbanBacklog getBacklog(String workStream){
    // TODO Why a board builder to build the backlog screen?
    KanbanBoardBuilder kanbanBoardBuilder = new KanbanBoardBuilder(new KanbanBoardColumnList(new KanbanBoardColumn(getRootWorkItemType(), getRootWorkItemType().getBacklogPhase())), workItemTypes, tree);
    KanbanBacklog backlog = kanbanBoardBuilder.buildKanbanBacklog(workStream);
    return backlog;
}


@Override
public void move(int id,int targetId,boolean after){
    tree.move(tree.getWorkItem(id), tree.getWorkItem(targetId), after);
}


public WorkItem getWorkItemById(int id){
    return getWorkItemTree().getWorkItem(id);
}


@Override
public void addJournalItem(KanbanJournalItem journalItem){
    try {
        List<KanbanJournalItem> journal = persistence.journalRead();
        journalItem.setId(nextJournalId(journal));
        journal.add(journalItem);
        persistence.journalWrite(journal);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@Override
public WorkItemTree getWorkItemTree(){
    return this.tree;
}


@Override
public List<KanbanJournalItem> getJournal(){
    try {
        return persistence.journalRead();
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}


@Override
public void stop(int id){
    tree.getWorkItem(id).stop();
}


@Override
public LocalDate getProjectedEndDate(LocalDate startDate,LocalDate endDate){
    BurnUpDataModel model = new BurnUpDataModel(getRootWorkItemType(), tree.getWorkItemList(), startDate, endDate);
    ProjectedDatasetPopulator projectedDatasetPopulator = new ProjectedDatasetPopulator(model);
    return projectedDatasetPopulator.getProjectedEndDate();
}


@Override
public int addWorkItem(int parentId,WorkItemType type,String itemName,int averageCaseEstimate,int worstCaseEstimate,int importance,String notes,String color,boolean excluded,String workStreams,LocalDate backlogDate){
    int newId = tree.getNewId();
    WorkItem workItem = new WorkItem(newId, parentId, type);
    workItem.setName(itemName);
    workItem.setAverageCaseEstimate(averageCaseEstimate);
    workItem.setWorstCaseEstimate(worstCaseEstimate);
    workItem.setImportance(importance);
    workItem.setNotes(notes);
    workItem.setColour(color);
    workItem.setExcluded(excluded);
    workItem.setWorkStreamsAsString(workStreams);
    tree.addWorkItem(workItem);
    // add the WorkItem onto the board and log the date
    advance(newId, backlogDate);
    return newId;
}


public int nextJournalId(List<KanbanJournalItem> journal){
    int max = 0;
    for (KanbanJournalItem item : journal) {
        max = Math.max(max, item.getId());
    }
    return max + 1;
}


@Override
public void deleteWorkItem(int id){
    tree.delete(id);
}


}