package com.kingen.bean.workflow;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingen.util.DateUtils;
@Entity
@Table(name = "T_VACATION")
public class Vacation extends BaseVOimplements Serializable,ActivitiAware{

 private  long serialVersionUID;

 public  int TYPE_PAID;

 public  int TYPE_SICK;

 public  int TYPE_MATTER;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID", unique = true)
 private  Integer id;

@Column(name = "WORK_DAYS")
 private  Integer days;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
@JsonFormat(pattern = "yyyy-MM-dd")
@Column(name = "BEGIN_DATE")
 private  Date beginDate;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
@JsonFormat(pattern = "yyyy-MM-dd")
@Column(name = "END_DATE")
 private  Date endDate;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name = "APPLY_DATE")
 private  Date applyDate;

@Column(name = "VAC_TYPE")
 private  Integer vacationType;

@Column(name = "REASON")
 private  String reason;

@Column(name = "PROC_INST_ID")
 private  String processInstanceId;

@Column(name = "USER_ID")
 private  String userId;

@Column(name = "STATUS")
 private  String status;

public Vacation() {
}
public Integer getVacationType(){
    return vacationType;
}


@Override
public String getContract(){
    // TODO Auto-generated method stub
    return null;
}


public void setVacationType(Integer vacationType){
    this.vacationType = vacationType;
}


public Integer getId(){
    return id;
}


public Date getEndDate(){
    // return DateUtils.toDate(endDate, "yyyy-MM-dd");
    return endDate;
}


public String getStatus(){
    return status;
}


@Override
public String getClientUint(){
    // TODO Auto-generated method stub
    return null;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public void setDays(Integer days){
    this.days = days;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setReason(String reason){
    this.reason = reason;
}


public void setId(Integer id){
    this.id = id;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getReason(){
    return reason;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public Date getApplyDate(){
    return applyDate;
}


public void setStatus(String status){
    this.status = status;
}


public Date getBeginDate(){
    // return DateUtils.toDate(beginDate, "yyyy-MM-dd");
    return beginDate;
}


public void setBeginDate(Date beginDate){
    this.beginDate = beginDate;
}


public Integer getDays(){
    return days;
}


@Override
public String getApplyDateStr(){
    return DateUtils.formatDateTime(this.applyDate);
}


@Override
public String getBusinessName(){
    // TODO Auto-generated method stub
    return null;
}


@Override
public String getPriority(){
    // TODO Auto-generated method stub
    return null;
}


public void setApplyDate(Date applyDate){
    this.applyDate = applyDate;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}