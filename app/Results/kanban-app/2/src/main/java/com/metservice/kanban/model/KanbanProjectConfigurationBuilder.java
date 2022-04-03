package com.metservice.kanban.model;
 import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.metservice.kanban.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
public class KanbanProjectConfigurationBuilder {

 private  File projectHome;

 private  KanbanPropertiesFile properties;

public KanbanProjectConfigurationBuilder(File kanbanHome, String projectName) throws IOException {
    projectHome = new File(kanbanHome, projectName);
    properties = new KanbanPropertiesFile(new File(projectHome, "kanban.properties"));
}
public KanbanBoardConfiguration getBoardDefinitions(WorkItemTypeCollection workItemTypes){
    Map<String, WorkItemType> workItemTypesByPhase = new HashMap<String, WorkItemType>();
    Map<String, Integer> wipLimitsByPhase = new HashMap<String, Integer>();
    for (WorkItemType type : workItemTypes) {
        String[] phases = properties.getPhases(type.getName());
        String[] columnLimits = properties.getPhaseWIPLimit(type.getName());
        int wipLimit;
        for (int i = 0; i < phases.length; i++) {
            try {
                if (columnLimits != null) {
                    wipLimit = Integer.parseInt(columnLimits[i]);
                } else {
                    wipLimit = -1;
                }
            } catch (Exception e) {
                // No limit was specified, or it was ""
                wipLimit = -1;
            }
            wipLimitsByPhase.put(phases[i], wipLimit);
            workItemTypesByPhase.put(phases[i], type);
        }
    }
    KanbanBoardConfiguration phaseSequences = new KanbanBoardConfiguration();
    for (BoardIdentifier board : BoardIdentifier.values()) {
        List<KanbanBoardColumn> columns = new ArrayList<KanbanBoardColumn>();
        String[] boardPhases = properties.getPhaseSequence(board);
        String phase = "";
        for (int i = 0; i < boardPhases.length; i++) {
            phase = boardPhases[i];
            columns.add(new KanbanBoardColumn(workItemTypesByPhase.get(phase), phase, wipLimitsByPhase.get(phase)));
        }
        phaseSequences.add(board, new KanbanBoardColumnList(columns));
    }
    return phaseSequences;
}


public Iterable<String> getPhases(Iterable<WorkItemType> workItemTypes){
    Collection<String> phases = new ArrayList<String>();
    for (WorkItemType workItemType : workItemTypes) {
        for (String phase : properties.getPhases(workItemType.getName())) {
            phases.add(phase);
        }
    }
    return phases;
}


public List<TreeNode<WorkItemType>> getChildWorkItemTypeTreeNodes(String name){
    List<TreeNode<WorkItemType>> children = new ArrayList<TreeNode<WorkItemType>>();
    // get all the work item types i.e feature,story
    for (String possibleChildName : properties.getWorkItemTypes()) {
        if (properties.isChildWorkItemType(name, possibleChildName)) {
            children.add(createWorkItemTypeTreeNode(possibleChildName));
        }
    }
    return children;
}


public KanbanProjectConfiguration buildConfiguration(){
    TreeNode<WorkItemType> rootWorkItemType = getRootWorkItemType();
    WorkItemTypeCollection workItemTypes = new WorkItemTypeCollection(rootWorkItemType);
    KanbanBoardConfiguration boardDefinitions = getBoardDefinitions(workItemTypes);
    return new KanbanProjectConfiguration(projectHome, boardDefinitions, rootWorkItemType, workItemTypes);
}


public TreeNode<WorkItemType> getRootWorkItemType(){
    // There should only be one root work item type (something like product, epic, MMF, etc.) The configuration
    // file format does not enforce this so we initially assume there are multiple roots and throw exceptions if
    // we find none or more than one.
    List<TreeNode<WorkItemType>> roots = getChildWorkItemTypeTreeNodes("root");
    if (roots.isEmpty()) {
        throw new IOException("no root work item type");
    } else if (roots.size() > 1) {
        throw new IOException("multiple root work item types: " + roots);
    }
    return roots.get(0);
}


public TreeNode<WorkItemType> createWorkItemTypeTreeNode(String name){
    WorkItemType workItemType = new WorkItemType(properties.getPhases(name));
    workItemType.setName(name);
    workItemType.setCardColour(properties.getWorkItemTypeCardColour(name));
    workItemType.setBackgroundColour(properties.getWorkItemTypeBackgroundColour(name));
    List<TreeNode<WorkItemType>> children = getChildWorkItemTypeTreeNodes(name);
    return TreeNode.create(WorkItemType.class, workItemType, children);
}


}