package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersQueryLogDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String queryBy;

@Temporal(TemporalType.DATE)
 private  Date queryDt;

 private  String queryReason;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersQueryLogDTO() {
}
public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public String getQueryReason(){
    return this.queryReason;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public void setQueryReason(String queryReason){
    this.queryReason = queryReason;
}


public String getQueryBy(){
    return this.queryBy;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setQueryBy(String queryBy){
    this.queryBy = queryBy;
}


public String getRptNo(){
    return this.rptNo;
}


public Date getQueryDt(){
    return this.queryDt;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setQueryDt(Date queryDt){
    this.queryDt = queryDt;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}