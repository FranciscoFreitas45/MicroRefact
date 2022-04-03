package com.hmm.DTO;
 import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
public class Bcard {

 private  Long bCardid;

 private  Float worktime;

 private  Date ontudytime;

 private  Date offdutytime;

 private  Float overtime;

 private  String calendar;

 private  String bcardType;

 private  Date workDate;

 private  Employee employ;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  Date applyTime;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

 private  String processInstanceId;

 private Integer emp_idR9B4;


public void setbCardid(Long bCardid){
    this.bCardid = bCardid;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public String getApproval(){
    return approval;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setOvertime(Float overtime){
    this.overtime = overtime;
}


public void setEmploy(Employee employ){
    this.employ = employ;
}


public String getCalendar(){
    return calendar;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getbCardid(){
    return bCardid;
}


public Date getApplyTime(){
    return applyTime;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
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


@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
@JoinColumn(name = "emp_id")
public Employee getEmploy(){
    return employ;
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


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
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


public Date getRealityStartTime(){
    return realityStartTime;
}


public void setWorkDate(Date workDate){
    this.workDate = workDate;
}


public void setUserId(String userId){
    this.userId = userId;
}


}