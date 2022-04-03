package com.hmm.Work.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.entity.LeaveEmpDTO;
public class BcardDTO {

 private  Long bCardid;

 private  Float worktime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date ontudytime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date offdutytime;

 private  Float overtime;

 private  String calendar;

 private  String bcardType;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date workDate;

 private  String empName;

 private  String empNo;

 private  String deptName;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date applyTime;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date realityStartTime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date realityEndTime;

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  String taskDefinitionKey;

 private  String processInstanceId;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;


public void setbCardid(Long bCardid){
    this.bCardid = bCardid;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void entityToDto(Bcard entity,BcardDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setOvertime(Float overtime){
    this.overtime = overtime;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public String getAssignee(){
    return assignee;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


public String getCalendar(){
    return calendar;
}


public Long getbCardid(){
    return bCardid;
}


public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public String getEmpNo(){
    return empNo;
}


public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public void setVersion(int version){
    this.version = version;
}


public Float getWorktime(){
    return worktime;
}


public String getDeptName(){
    return deptName;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setTaskName(String taskName){
    this.taskName = taskName;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public Date getWorkDate(){
    return workDate;
}


public String getBcardType(){
    return bcardType;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


public Date getOntudytime(){
    return ontudytime;
}


public void dtoToEntity(BcardDTO dto,Bcard entity){
    BeanUtils.copyProperties(dto, entity);
}


public void setUserId(String userId){
    this.userId = userId;
}


public Date getRealityStartTime(){
    return realityStartTime;
}


public String getTaskName(){
    return taskName;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public String getApproval(){
    return approval;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


public Date getTaskCreateTime(){
    return taskCreateTime;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setWorktime(Float worktime){
    this.worktime = worktime;
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


public Date getOffdutytime(){
    return offdutytime;
}


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public boolean isSuspended(){
    return suspended;
}


public Float getOvertime(){
    return overtime;
}


public String getReason(){
    return reason;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setBcardType(String bcardType){
    this.bcardType = bcardType;
}


public void setApproval(String approval){
    this.approval = approval;
}


public String getUserId(){
    return userId;
}


public void setWorkDate(Date workDate){
    this.workDate = workDate;
}


}