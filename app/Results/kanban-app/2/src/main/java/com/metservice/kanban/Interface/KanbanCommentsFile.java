package com.metservice.kanban.Interface;
public interface KanbanCommentsFile {

   public void readAllComments();
   public List<WorkItemComment> getCommentsFor(int id);
   public void writeAllComments(WorkItemTree workItems);
}