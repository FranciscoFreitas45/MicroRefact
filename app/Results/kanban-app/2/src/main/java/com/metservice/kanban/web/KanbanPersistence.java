package com.metservice.kanban.web;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.metservice.kanban.KanbanCommentsFile;
import com.metservice.kanban.KanbanJournalFile;
import com.metservice.kanban.csv.KanbanCsvFile;
import com.metservice.kanban.model.DefaultWorkItemTree;
import com.metservice.kanban.model.KanbanJournalItem;
import com.metservice.kanban.model.KanbanProjectConfiguration;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemTree;
import com.metservice.kanban.model.WorkItemType;
import com.metservice.kanban.Interface.KanbanCommentsFile;
import com.metservice.kanban.DTO.KanbanCsvFile;
public class KanbanPersistence {

 private  Collection<KanbanCsvFile> files;

 private  KanbanJournalFile journalFile;

 private  KanbanCommentsFile commentsFile;

/**
 * Default constructor for KanbanPersistence. Reads filenames from a given KanbanProjectConfiguration
 * and populates the list of KanbanCsvFiles.
 * @param configuration - the project conguration
 * @throws IOException
 */
public KanbanPersistence(KanbanProjectConfiguration configuration) throws IOException {
    for (WorkItemType workItemType : configuration.getWorkItemTypes()) {
        KanbanCsvFile file = new KanbanCsvFile(configuration.getDataFile(workItemType), workItemType);
        files.add(file);
        // should this be done for every Work Item Type?!?
        journalFile = configuration.getKanbanJournalFile();
    }
    commentsFile = configuration.getKanbanCommentsFile();
}
public void journalWrite(List<KanbanJournalItem> items){
    journalFile.setJournalItems(items);
    journalFile.writeJournal();
}


public WorkItemTree read(){
    commentsFile.readAllComments();
    WorkItemTree index = new DefaultWorkItemTree();
    for (KanbanCsvFile file : files) {
        List<WorkItem> workItems = file.read();
        for (WorkItem workItem : workItems) {
            index.addWorkItem(workItem);
            workItem.resetCommentsAndReplaceWith(commentsFile.getCommentsFor(workItem.getId()));
        }
    }
    return index;
}


public void write(WorkItemTree workItems){
    for (KanbanCsvFile file : files) {
        List<WorkItem> workItemsForItem = workItems.getWorkItemsOfType(file.getWorkItemType(), null);
        file.write(workItemsForItem);
    }
    commentsFile.writeAllComments(workItems);
}


public Collection<KanbanCsvFile> getFiles(){
    return files;
}


public List<KanbanJournalItem> journalRead(){
    return journalFile.getJournalItems();
}


}