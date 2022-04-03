package com.hmm.Work.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
public class BcardEmpDTO {

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

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date realityStartTime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date realityEndTime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date applyTime;

 private  String empName;

 private  String empNo;

 private  String deptName;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

 private  String processInstanceId;


public void setbCardid(Long bCardid){
    this.bCardid = bCardid;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void entityToDto(Bcard entity,BcardEmpDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public String getApproval(){
    return approval;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public void setOvertime(Float overtime){
    this.overtime = overtime;
}


public String getCalendar(){
    return calendar;
}


public String getEmpName(){
    return empName;
}


public Long getbCardid(){
    return bCardid;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public void setWorktime(Float worktime){
    this.worktime = worktime;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setReason(String reason){
    this.reason = reason;
}


public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public Date getOffdutytime(){
    return offdutytime;
}


public Float getOvertime(){
    return overtime;
}


public String getReason(){
    return reason;
}


public Float getWorktime(){
    return worktime;
}


public String getDeptName(){
    return deptName;
}


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
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


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


public void setBcardType(String bcardType){
    this.bcardType = bcardType;
}


public Date getOntudytime(){
    return ontudytime;
}


public void setApproval(String approval){
    this.approval = approval;
}


public String getUserId(){
    return userId;
}


public void dtoToEntity(BcardEmpDTO dto,Bcard entity){
    BeanUtils.copyProperties(dto, entity);
}


public void setWorkDate(Date workDate){
    this.workDate = workDate;
}


public Date getRealityStartTime(){
    return realityStartTime;
}


public void setUserId(String userId){
    this.userId = userId;
}


}