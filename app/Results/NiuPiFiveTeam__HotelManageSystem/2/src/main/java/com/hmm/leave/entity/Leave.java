package com.hmm.leave.entity;
 import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
@Entity
@Table(name = "t_leave")
public class Leave {

 private  Long id;

 private  Date startTime;

 private  Date endTime;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  Date applyTime;

 private  String leaveType;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

 private  String processInstanceId;

 private  Employee employ;

@Column(name = "emp_idB9PD")
 private Integer emp_idB9PD;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public String getApproval(){
    return approval;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setLeaveType(String leaveType){
    this.leaveType = leaveType;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setEmploy(Employee employ){
this.emp_idB9PD = employ.getEmploy() ;
employeerequest.setEmploy(employ,this.emp_idB9PD);
 this.employ = employ;
}



@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getEndTime(){
    return endTime;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setReason(String reason){
    this.reason = reason;
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


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idB9PD);
return this.employ;
}}



public String getReason(){
    return reason;
}


public String getLeaveType(){
    return leaveType;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setApproval(String approval){
    this.approval = approval;
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