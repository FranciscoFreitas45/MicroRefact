package com.metservice.kanban;
 import java.util.Arrays.asList;
import org.apache.commons.io.FileUtils.writeStringToFile;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import com.metservice.kanban.utils.MessageUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.metservice.kanban.model.DefaultKanbanProject;
import com.metservice.kanban.model.KanbanBoardConfiguration;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.KanbanProjectConfiguration;
import com.metservice.kanban.model.KanbanProjectConfigurationBuilder;
import com.metservice.kanban.model.TreeNode;
import com.metservice.kanban.model.WorkItemTree;
import com.metservice.kanban.model.WorkItemType;
import com.metservice.kanban.model.WorkItemTypeCollection;
import com.metservice.kanban.web.KanbanPersistence;
public class KanbanService {

 public  String KANBAN_HOME_PROPERTY_NAME;

 private  NotFileFilter NO_DOT_DIRS_NO_FILES;

 private  String KANBAN_PROPERTIES_FILE_NAME;

 private  File home;

 private  String version;

public KanbanService() {
    this(getKanbanHomeFromSystemProperty());
}/**
 * Constructor for {@link KanbanService}.
 *
 * @param home
 *            - When passed the home File the <code>home</code> and
 *            <code>version</code> fields are set.
 */
public KanbanService(File home) {
    this.home = home;
    this.version = loadKanbanVersion();
}
public String getVersion(){
    return version;
}


public KanbanProject getKanbanProject(String projectName){
    KanbanProjectConfiguration configuration = getProjectConfiguration(projectName);
    TreeNode<WorkItemType> rootWorkItemType = configuration.getRootWorkItemType();
    KanbanBoardConfiguration phaseSequences = configuration.getPhaseSequences();
    // Creates a new KanbanPersistence object using the configuration file.
    // Then, creates a WorkItemTree using the persistence file.
    KanbanPersistence persistence = new KanbanPersistence(configuration);
    WorkItemTree tree = persistence.read();
    WorkItemTypeCollection workItemTypes = createWorkItemTypeCollection(rootWorkItemType);
    return new DefaultKanbanProject(workItemTypes, phaseSequences, tree, persistence, projectName);
}


public void editProject(String projectName,String settings){
    File projectHome = new File(home, projectName);
    if (projectHome.exists()) {
        File file = new File(projectHome, KANBAN_PROPERTIES_FILE_NAME);
        writeStringToFile(file, settings);
    } else {
        throw new IllegalArgumentException("cannot edit a project that does not exist: " + projectName);
    }
}


public Collection<String> getProjects(){
    if (home.exists()) {
        List<String> result = asList(home.list(NO_DOT_DIRS_NO_FILES));
        Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
        return result;
    } else {
        return new ArrayList<String>();
    }
}


public boolean isFilteredProject(String p){
    return p.startsWith("_");
}


public void createProject(String newProjectName,String settings){
    File newProjectHome = new File(home, newProjectName);
    if (newProjectHome.exists()) {
        throw new IllegalArgumentException("cannot create a project with the same name as an existing project: " + newProjectName);
    }
    // Creates the project folder and writes the settings to '[newProjectName].kanban.properties'
    try {
        if (!newProjectHome.mkdir()) {
            throw new IllegalArgumentException("cannot create project " + MessageUtils.decorateSingleQuotes(newProjectName) + ". " + "Please check permissions on folder " + MessageUtils.decorateSingleQuotes(newProjectHome.getParentFile().getAbsolutePath()));
        }
    } catch (SecurityException e) {
        throw new IllegalArgumentException("cannot create project " + MessageUtils.decorateSingleQuotes(newProjectName) + ". " + "Please check permissions on folder " + MessageUtils.decorateSingleQuotes(newProjectHome.getParentFile().getAbsolutePath()));
    }
    File file = new File(newProjectHome, KANBAN_PROPERTIES_FILE_NAME);
    writeStringToFile(file, settings);
}


public void setColumnWipLimit(String projectName,WorkItemType workItemtype,String columnName,Integer wipLimit){
    getProjectConfiguration(projectName).getKanbanPropertiesFile().setColumnWipLimit(workItemtype, columnName, wipLimit);
}


public void renameProject(String projectName,String newProjectName){
    File projectHome = new File(home, projectName);
    File newProjectHome = new File(home, newProjectName);
    if (newProjectHome.exists()) {
        throw new IllegalArgumentException("project name already exists: " + newProjectName);
    }
    projectHome.renameTo(newProjectHome);
}


public File getHome(){
    return home;
}


public boolean renameColumn(String projectName,WorkItemType workItemType,String columnName,String newColumnName){
    // check if column exists
    if (!workItemType.containsPhase(columnName)) {
        return false;
    }
    if (workItemType.containsPhase(newColumnName)) {
        return false;
    }
    // rename column in a properties file
    try {
        getProjectConfiguration(projectName).getKanbanPropertiesFile().renameColumn(workItemType, columnName, newColumnName);
    } catch (IllegalArgumentException e) {
        return false;
    }
    // rename column in a CSV file
    KanbanProjectConfiguration configuration = getProjectConfiguration(projectName);
    CSVReader csvReader = new CSVReader(new FileReader(configuration.getDataFile(workItemType)));
    List<String[]> items;
    try {
        items = csvReader.readAll();
        for (int i = 0; i < items.get(0).length; i++) {
            if (items.get(0)[i].equals(columnName)) {
                items.get(0)[i] = newColumnName;
                break;
            }
        }
    } finally {
        csvReader.close();
    }
    CSVWriter csvWriter = new CSVWriter(new FileWriter(configuration.getDataFile(workItemType)));
    try {
        csvWriter.writeAll(items);
    } finally {
        csvWriter.close();
    }
    return true;
}


public String loadKanbanVersion(){
    Properties versionProperties = new Properties();
    try {
        versionProperties.load(getClass().getResourceAsStream("/version.txt"));
    } catch (IOException e) {
        return "";
    }
    return versionProperties.getProperty("version");
}


public KanbanProjectConfiguration getProjectConfiguration(String projectName){
    KanbanProjectConfigurationBuilder configurationBuilder = new KanbanProjectConfigurationBuilder(home, projectName);
    return configurationBuilder.buildConfiguration();
}


public Collection<String> getFilteredProjects(){
    Collection<String> projects = getProjects();
    Collection<String> filteredProjects = new ArrayList<String>();
    for (String p : projects) {
        if (!isFilteredProject(p)) {
            filteredProjects.add(p);
        }
    }
    return filteredProjects;
}


public File getKanbanHomeFromSystemProperty(){
    String kanbanHomePath = System.getProperty(KANBAN_HOME_PROPERTY_NAME);
    if (kanbanHomePath == null) {
        return new File(System.getProperty("user.home"), ".kanban");
    } else {
        return new File(kanbanHomePath);
    }
}


public WorkItemTypeCollection createWorkItemTypeCollection(TreeNode<WorkItemType> rootWorkItemType){
    return new WorkItemTypeCollection(rootWorkItemType);
}


}