package com.metservice.kanban.model;
 import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.joda.time.LocalDate;
public interface KanbanProject {


public LocalDate getStartDate()
;

public WorkItemType getChildType(WorkItemType parentType)
;

public void move(int id,int targetId,boolean after)
;

public void reparentWorkItem(int id,int newParentId)
;

public KanbanBoard getBoard(BoardIdentifier boardType,String workStream)
;

public void save()
;

public void deleteJournalItem(int itemId)
;

public void reorder(Integer id,Integer[] newIdList)
;

public WorkItem getWorkItemById(int id)
;

public void addJournalItem(KanbanJournalItem journalItem)
;

public WorkItemTypeCollection getWorkItemTypes()
;

public Set<String> getWorkStreams()
;

public void advance(int id,LocalDate date)
;

public KanbanBoardColumnList getColumns(BoardIdentifier boardType)
;

public WorkItemTree getWorkItemTree()
;

public List<KanbanJournalItem> getJournal()
;

public void updateJournalItem(KanbanJournalItem journalItem)
;

public void stop(int id)
;

public LocalDate getProjectedEndDate(LocalDate startDate,LocalDate endDate)
;

public KanbanBoardColumnList getWallColumns()
;

public int addWorkItem(int parentId,WorkItemType type,String name,int averageCaseEstimate,int worstCaseEstimate,int importance,String notes,String color,boolean excluded,String workStreams,LocalDate backlogDate)
;

public KanbanBoard getCompleted(String workStream)
;

public KanbanBacklog getBacklog(String workStream)
;

public void deleteWorkItem(int i)
;

}