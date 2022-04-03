package com.hmm.leave.entity;
 import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
public class LeaveDTO {

 private  Long id;

 private  String userId;

 private  Date applyTime;

 private  Date startTime;

 private  Date endTime;

 private  String leaveType;

 private  String reason;

 private  String empName;

 private  String empNo;

 private  String deptName;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  ProcessStatus processStatus;

 private  String taskDefinitionKey;

 private  String processInstanceId;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;


public String getTaskName(){
    return taskName;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public boolean getSuspended(){
    return suspended;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public void setLeaveType(String leaveType){
    this.leaveType = leaveType;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


public Long getId(){
    return id;
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


public String getEmpName(){
    return empName;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public void setEmpName(String empName){
    this.empName = empName;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getEndTime(){
    return endTime;
}


public String getEmpNo(){
    return empNo;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setTaskCreateTime(Date taskCreateTime){
    this.taskCreateTime = taskCreateTime;
}


public void setReason(String reason){
    this.reason = reason;
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public void setId(Long id){
    this.id = id;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public void setVersion(int version){
    this.version = version;
}


public String getReason(){
    return reason;
}


public String getLeaveType(){
    return leaveType;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


public String getDeptName(){
    return deptName;
}


public void setTaskName(String taskName){
    this.taskName = taskName;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public String getUserId(){
    return userId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityStartTime(){
    return realityStartTime;
}


public void setUserId(String userId){
    this.userId = userId;
}


}