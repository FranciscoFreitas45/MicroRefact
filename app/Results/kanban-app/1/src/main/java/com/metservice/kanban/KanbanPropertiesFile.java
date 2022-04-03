package com.metservice.kanban;
 import java.lang.String.format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import com.metservice.kanban.model.BoardIdentifier;
import com.metservice.kanban.model.HtmlColour;
import com.metservice.kanban.model.WorkItemType;
public class KanbanPropertiesFile {

 private  Logger LOGGER;

 private  File file;

 private  Properties properties;

public KanbanPropertiesFile(File file) throws IOException {
    this(new FileReader(file));
    this.file = file;
}public KanbanPropertiesFile(Reader reader) throws IOException {
    try {
        properties.load(reader);
    } finally {
        reader.close();
    }
}
public void storeProperties(){
    if (file != null) {
        FileWriter writer = new FileWriter(file);
        try {
            properties.store(writer, "");
        } finally {
            writer.close();
        }
    }
}


public String getParentWorkItemType(String workItemType){
    String propertyKey = format("workItemTypes.%s.parent", workItemType);
    return getString(propertyKey);
}


public String[] getPhaseSequence(BoardIdentifier boardType){
    String propertyKey = format("boards.%s", boardType.getName());
    return getCommaSeparatedStrings(propertyKey);
}


public String getString(String propertyKey){
    String propertyValue = properties.getProperty(propertyKey);
    // if (propertyValue == null) {
    // throw new IOException("property \"" + propertyKey + "\" missing from " + file);
    // }
    return propertyValue;
}


public String[] getWorkItemTypes(){
    return getCommaSeparatedStrings("workItemTypes");
}


public void setColumnWipLimit(WorkItemType workItemType,String columnName,Integer wipLimit){
    LOGGER.info("Setting WIP limit for column {}.{} to {}", new Object[] { workItemType.toString(), columnName, wipLimit });
    String[] phases = getPhases(workItemType.toString());
    String[] wipLimits = getCommaSeparatedStrings("workItemTypes." + workItemType + ".wipLimit");
    if (wipLimits == null) {
        wipLimits = new String[phases.length];
    }
    if (wipLimits.length < phases.length) {
        wipLimits = Arrays.copyOf(wipLimits, phases.length);
    }
    for (int i = 0; i < phases.length; i++) {
        if (phases[i].equals(columnName)) {
            if (wipLimit == null) {
                wipLimits[i] = "";
            } else {
                wipLimits[i] = wipLimit.toString();
            }
            break;
        }
    }
    String wipLimitsStr = StringUtils.join(wipLimits, ",");
    properties.put(String.format("workItemTypes.%s.wipLimit", workItemType.getName()), wipLimitsStr);
    storeProperties();
}


public boolean isChildWorkItemType(String name,String possibleChildName){
    return getParentWorkItemType(possibleChildName).equals(name);
}


public String[] getPhases(String workItemType){
    String propertyKey = format("workItemTypes.%s.phases", workItemType);
    return getCommaSeparatedStrings(propertyKey);
}


public HtmlColour getWorkItemTypeCardColour(String workItemType){
    String propertyKey = format("workItemTypes.%s.cardColour", workItemType);
    return new HtmlColour(getString(propertyKey));
}


public String[] getPhaseWIPLimit(String workItemType){
    String propertyKey = format("workItemTypes.%s.wipLimit", workItemType);
    try {
        return getCommaSeparatedStrings(propertyKey);
    } catch (Exception e) {
        return new String[0];
    }
}


public String getContentAsString(){
    BufferedReader in = new BufferedReader(new FileReader(file));
    StringBuffer sb = new StringBuffer();
    String str;
    while ((str = in.readLine()) != null) {
        sb.append(str + "\n");
    }
    in.close();
    return sb.toString();
}


public HtmlColour getWorkItemTypeBackgroundColour(String workItemType){
    String propertyKey = format("workItemTypes.%s.backgroundColour", workItemType);
    return new HtmlColour(getString(propertyKey));
}


public void renameColumn(WorkItemType workItemType,String columnName,String newColumnName){
    LOGGER.info("Renaming column for WorkItem {} from {} to {} in properties file", new Object[] { workItemType, columnName, newColumnName });
    boolean columnFound = false;
    String[] phases = getCommaSeparatedStrings(String.format("workItemTypes.%s.phases", workItemType.getName()));
    for (int i = 0; i < phases.length; i++) {
        if (phases[i].equals(columnName)) {
            phases[i] = newColumnName;
            columnFound = true;
            break;
        }
    }
    Assert.isTrue(columnFound, String.format("Column %s cannot be found in properties file", columnName));
    String newPhases = StringUtils.join(phases, ",");
    properties.put(String.format("workItemTypes.%s.phases", workItemType.getName()), newPhases);
    String[] boardPhases = getCommaSeparatedStrings("boards.wall");
    for (int i = 0; i < boardPhases.length; i++) {
        if (boardPhases[i].equals(columnName)) {
            boardPhases[i] = newColumnName;
        }
    }
    String newboardPhases = StringUtils.join(boardPhases, ",");
    properties.put("boards.wall", newboardPhases);
    storeProperties();
}


public String[] getCommaSeparatedStrings(String propertyKey){
    String commaSeparatedString = getString(propertyKey);
    return StringUtils.splitPreserveAllTokens(commaSeparatedString, ',');
}


}