package com.metservice.kanban.csv;
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

public KanbanCsvFile(File file, WorkItemType workItemType) {
    this.file = file;
    this.workItemType = workItemType;
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
}


@Override
public String toString(){
    return file.toString();
}


public void cleanUpTemproaryFiles(){
    File directory = file.getParentFile();
    List<String> files = Arrays.asList(directory.list());
    Collections.sort(files);
    Collections.reverse(files);
    Pattern tempFilePattern = Pattern.compile(file.getName() + "\\.\\d+\\.temp");
    int tempFilesFound = 0;
    File fileToClean;
    for (String aFile : files) {
        fileToClean = new File(directory.getAbsolutePath() + File.separatorChar + aFile);
        if (tempFilePattern.matcher(aFile).matches()) {
            tempFilesFound++;
            if (tempFilesFound > MAX_TEMPORARY_FILES) {
                fileToClean.delete();
            }
        }
    }
}


public WorkItemType getWorkItemType(){
    return workItemType;
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
}


}