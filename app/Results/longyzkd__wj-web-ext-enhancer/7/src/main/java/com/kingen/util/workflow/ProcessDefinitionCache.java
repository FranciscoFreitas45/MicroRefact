package com.kingen.util.workflow;
 import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
@Component
public class ProcessDefinitionCache {

 private  Map<String,ProcessDefinition> map;

 private  Map<String,List<ActivityImpl>> activities;

 private  Map<String,ActivityImpl> singleActivity;

 private  RepositoryService repositoryService;


public ActivityImpl getActivity(String processDefinitionId,String activityId){
    ProcessDefinition processDefinition = get(processDefinitionId);
    if (processDefinition != null) {
        ActivityImpl activityImpl = singleActivity.get(processDefinitionId + "_" + activityId);
        if (activityImpl != null) {
            return activityImpl;
        }
    }
    return null;
}


@Autowired
public void setRepositoryService(RepositoryService repositoryService){
    ProcessDefinitionCache.repositoryService = repositoryService;
}


public String getActivityName(String processDefinitionId,String activityId){
    ActivityImpl activity = getActivity(processDefinitionId, activityId);
    if (activity != null) {
        return ObjectUtils.toString(activity.getProperty("name"));
    }
    return null;
}


public ProcessDefinition get(String processDefinitionId){
    ProcessDefinition processDefinition = map.get(processDefinitionId);
    if (processDefinition == null) {
        processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
        if (processDefinition != null) {
            put(processDefinitionId, processDefinition);
        }
    }
    return processDefinition;
}


public void put(String processDefinitionId,ProcessDefinition processDefinition){
    map.put(processDefinitionId, processDefinition);
    ProcessDefinitionEntity pde = (ProcessDefinitionEntity) processDefinition;
    activities.put(processDefinitionId, pde.getActivities());
    for (ActivityImpl activityImpl : pde.getActivities()) {
        singleActivity.put(processDefinitionId + "_" + activityImpl.getId(), activityImpl);
    }
}


}