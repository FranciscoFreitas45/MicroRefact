package com.metservice.kanban.DTO;
 import org.apache.commons.io.FileUtils.copyFile;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemType;
public class KanbanCsvFile {

 private  int MAX_TEMPORARY_FILES;

 private  File file;

 private  WorkItemType workItemType;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public KanbanCsvFile(File file, WorkItemType workItemType) {
    this.file = file;
    this.workItemType = workItemType;
}
public WorkItemType getWorkItemType(){
    return workItemType;
}


public List<WorkItem> read(Reader reader){
    KanbanCsvReader csvReader = new KanbanCsvReader(reader, workItemType);
    try {
        List<WorkItem> workItems = csvReader.read();
        csvReader.close();
        return workItems;
    } catch (IOException e) {
        throw new IOException("failure reading " + file, e);
    } catch (RuntimeException e) {
        throw new RuntimeException("failure reading " + file, e);
    } finally {
        csvReader.closeQuietly();
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/read"))

.queryParam("reader",reader)
;
List<WorkItem> aux = restTemplate.getForObject(builder.toUriString(),List<WorkItem>.class);
return aux;
}


public void write(List<WorkItem> workItems){
    File temporaryFile = new File(file.getAbsolutePath() + "." + System.currentTimeMillis() + ".temp");
    KanbanCsvWriter workItemWriter = new KanbanCsvWriter(new FileWriter(temporaryFile), workItemType);
    try {
        workItemWriter.write(workItems);
        workItemWriter.close();
    } catch (IOException e) {
        throw new IOException("failure writing " + file, e);
    } catch (RuntimeException e) {
        throw new RuntimeException("failure writing " + file, e);
    } finally {
        workItemWriter.closeQuietly();
    }
    file.delete();
    copyFile(temporaryFile, file);
    cleanUpTemproaryFiles();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/write"))

.queryParam("workItems",workItems)
;
restTemplate.put(builder.toUriString(),null);
}


}