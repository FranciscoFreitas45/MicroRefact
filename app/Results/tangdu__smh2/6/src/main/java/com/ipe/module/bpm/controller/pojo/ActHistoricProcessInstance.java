package com.ipe.module.bpm.controller.pojo;
 import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Map;
public class ActHistoricProcessInstance {

 private  String id;

 private String businessKey;

 private String processName;

 private String status;

 private String processDefinitionId;

 private Date startTime;

 private Date endTime;

 private Long durationInMillis;

 private String startUserId;

 private String startActivityId;

 private String deleteReason;

 private String superProcessInstanceId;

 private Map<String,Object> processVariables;


public String getProcessName(){
    return processName;
}


public void setProcessVariables(Map<String,Object> processVariables){
    this.processVariables = processVariables;
}


public String getId(){
    return id;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


public String getStatus(){
    return status;
}


public void setDurationInMillis(Long durationInMillis){
    this.durationInMillis = durationInMillis;
}


public void setDeleteReason(String deleteReason){
    this.deleteReason = deleteReason;
}


public void setStartUserId(String startUserId){
    this.startUserId = startUserId;
}


public String getDeleteReason(){
    return deleteReason;
}


public void setProcessName(String processName){
    this.processName = processName;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getEndTime(){
    return endTime;
}


public String getSuperProcessInstanceId(){
    return superProcessInstanceId;
}


public String getBusinessKey(){
    return businessKey;
}


public String getStartActivityId(){
    return startActivityId;
}


public void setId(String id){
    this.id = id;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setStartActivityId(String startActivityId){
    this.startActivityId = startActivityId;
}


public String getStartUserId(){
    return startUserId;
}


public Long getDurationInMillis(){
    return durationInMillis;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


public void setSuperProcessInstanceId(String superProcessInstanceId){
    this.superProcessInstanceId = superProcessInstanceId;
}


public void setStatus(String status){
    this.status = status;
}


public Map<String,Object> getProcessVariables(){
    return processVariables;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setBusinessKey(String businessKey){
    this.businessKey = businessKey;
}


}