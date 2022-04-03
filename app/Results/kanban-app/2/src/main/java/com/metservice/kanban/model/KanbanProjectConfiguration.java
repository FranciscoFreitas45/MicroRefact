package com.metservice.kanban.model;
 import java.io.File;
import java.io.IOException;
import com.metservice.kanban.KanbanCommentsFile;
import com.metservice.kanban.KanbanJournalFile;
import com.metservice.kanban.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
public class KanbanProjectConfiguration {

 private  File projectHome;

 private  KanbanBoardConfiguration phaseSequences;

 private  TreeNode<WorkItemType> rootWorkItemType;

 private  WorkItemTypeCollection workItemTypes;

public KanbanProjectConfiguration(File projectHome, KanbanBoardConfiguration phaseSequences, TreeNode<WorkItemType> rootWorkItemType, WorkItemTypeCollection workItemTypes) throws IOException {
    this.projectHome = projectHome;
    this.phaseSequences = phaseSequences;
    this.rootWorkItemType = rootWorkItemType;
    this.workItemTypes = workItemTypes;
}
public KanbanPropertiesFile getKanbanPropertiesFile(){
    return new KanbanPropertiesFile(new File(projectHome, "kanban.properties"));
}


public File getDataFile(WorkItemType workItemType){
    return new File(projectHome, workItemType.getName() + ".csv");
}


public KanbanCommentsFile getKanbanCommentsFile(){
    return new KanbanCommentsFile(new File(projectHome, "comments.store"));
}


public KanbanJournalFile getKanbanJournalFile(){
    return new KanbanJournalFile(new File(projectHome, "journal.csv"));
}


public TreeNode<WorkItemType> getRootWorkItemType(){
    return rootWorkItemType;
}


public WorkItemTypeCollection getWorkItemTypes(){
    return workItemTypes;
}


public KanbanBoardConfiguration getPhaseSequences(){
    return phaseSequences;
}


@Deprecated
public File getJournalFile(){
    File journalFile = new File(projectHome, "journal.txt");
    journalFile.createNewFile();
    return journalFile;
}


}