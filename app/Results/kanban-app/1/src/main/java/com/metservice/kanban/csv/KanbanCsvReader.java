package com.metservice.kanban.csv;
 import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemType;
public class KanbanCsvReader {

 private  CSVReader csvReader;

 private  WorkItemParser workItemParser;

public KanbanCsvReader(Reader reader, WorkItemType workItemType) {
    this(reader, new DefaultWorkItemParser(workItemType));
}public KanbanCsvReader(Reader reader, WorkItemParser workItemParser) {
    this.csvReader = new CSVReader(reader);
    this.workItemParser = workItemParser;
}
public List<WorkItem> read(){
    CsvColumnNames columnNames = new CsvColumnNames(csvReader.readNext());
    List<WorkItem> workItems = new ArrayList<WorkItem>();
    int rowNumber = 0;
    String[] row = csvReader.readNext();
    while (row != null) {
        WorkItem workItem = createWorkItemWrappingException(columnNames, rowNumber, row);
        workItems.add(workItem);
        row = csvReader.readNext();
        rowNumber++;
    }
    return new ArrayList<WorkItem>(workItems);
}


public WorkItem createWorkItemWrappingException(CsvColumnNames columnNames,int rowNumber,String[] row){
    try {
        return workItemParser.parseWorkItem(columnNames, row);
    } catch (RuntimeException e) {
        throw new RuntimeException("failed to create work item from row " + rowNumber, e);
    }
}


public void closeQuietly(){
    try {
        csvReader.close();
    } catch (Exception e) {
    }
}


public void close(){
    csvReader.close();
}


}