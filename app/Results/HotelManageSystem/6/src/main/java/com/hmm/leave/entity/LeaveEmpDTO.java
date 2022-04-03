package com.hmm.leave.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
public class LeaveEmpDTO {

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

 private  String approval;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  ProcessStatus processStatus;

 private  String processInstanceId;


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void entityToDto(Leave entity,LeaveEmpDTO dto){
    BeanUtils.copyProperties(entity, dto);
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


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public Long getId(){
    return id;
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


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getEndTime(){
    return endTime;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
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


public String getReason(){
    return reason;
}


public String getLeaveType(){
    return leaveType;
}


public String getDeptName(){
    return deptName;
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


public void setApproval(String approval){
    this.approval = approval;
}


public String getUserId(){
    return userId;
}


public void dtoToEntity(LeaveEmpDTO dto,Leave entity){
    BeanUtils.copyProperties(dto, entity);
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityStartTime(){
    return realityStartTime;
}


public void setUserId(String userId){
    this.userId = userId;
}


}