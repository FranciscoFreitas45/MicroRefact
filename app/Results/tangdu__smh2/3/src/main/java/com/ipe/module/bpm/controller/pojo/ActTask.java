package com.ipe.module.bpm.controller.pojo;
 import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
public class ActTask {

 private int revision;

 private String owner;

 private String assignee;

 private String parentTaskId;

 private String name;

 private String description;

 private int priority;

 private Date createTime;

 private Date dueDate;

 private int suspensionState;

 private boolean isIdentityLinksInitialized;

 private String executionId;

 private String processInstanceId;

 private String processDefinitionId;

 private String taskDefinitionKey;

 private String eventName;


public void setName(String name){
    this.name = name;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setDueDate(Date dueDate){
    this.dueDate = dueDate;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public String getEventName(){
    return eventName;
}


public String getAssignee(){
    return assignee;
}


public void setParentTaskId(String parentTaskId){
    this.parentTaskId = parentTaskId;
}


public void setDescription(String description){
    this.description = description;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


public String getOwner(){
    return owner;
}


public String getDescription(){
    return description;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setExecutionId(String executionId){
    this.executionId = executionId;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public void setRevision(int revision){
    this.revision = revision;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public void setEventName(String eventName){
    this.eventName = eventName;
}


public int getRevision(){
    return revision;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getDueDate(){
    return dueDate;
}


public void setSuspensionState(int suspensionState){
    this.suspensionState = suspensionState;
}


public boolean isIdentityLinksInitialized(){
    return isIdentityLinksInitialized;
}


public String getExecutionId(){
    return executionId;
}


public int getSuspensionState(){
    return suspensionState;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


public void setIdentityLinksInitialized(boolean identityLinksInitialized){
    isIdentityLinksInitialized = identityLinksInitialized;
}


public int getPriority(){
    return priority;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public void setPriority(int priority){
    this.priority = priority;
}


public String getParentTaskId(){
    return parentTaskId;
}


}