package com.hmm.activiti.domain;
 import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class WorkflowDTO {

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  String taskDefinitionKey;

 private  String processInstanceId;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;

 private  String businessKey;


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public String getTaskName(){
    return taskName;
}


public boolean isSuspended(){
    return suspended;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public void setVersion(int version){
    this.version = version;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getTaskCreateTime(){
    return taskCreateTime;
}


public String getAssignee(){
    return assignee;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


public void setTaskName(String taskName){
    this.taskName = taskName;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public String getBusinessKey(){
    return businessKey;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setTaskCreateTime(Date taskCreateTime){
    this.taskCreateTime = taskCreateTime;
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public void setBusinessKey(String businessKey){
    this.businessKey = businessKey;
}


}