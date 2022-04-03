package com.hmm.travel.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
public class TravelEmpDTO {

 private  Long travelId;

 private  Date traStartTime;

 private  Date traEndTime;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String process;

 private  Float allowance;

 private  Date applyTime;

 private  String empName;

 private  String empNo;

 private  String deptName;

 private  String processInstanceId;


public void setTravelId(Long travelId){
    this.travelId = travelId;
}


public Float getAllowance(){
    return allowance;
}


public void setTraStartTime(Date traStartTime){
    this.traStartTime = traStartTime;
}


public void setTraEndTime(Date traEndTime){
    this.traEndTime = traEndTime;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void entityToDto(Travel entity,TravelEmpDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraStartTime(){
    return traStartTime;
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


public String getEmpName(){
    return empName;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
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


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public Long getTravelId(){
    return travelId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getProcess(){
    return process;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraEndTime(){
    return traEndTime;
}


public void setAllowance(Float allowance){
    this.allowance = allowance;
}


public void setProcess(String process){
    this.process = process;
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


public void setApproval(String approval){
    this.approval = approval;
}


public void dtoToEntity(TravelEmpDTO dto,Travel entity){
    BeanUtils.copyProperties(dto, entity);
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getRealityStartTime(){
    return realityStartTime;
}


}