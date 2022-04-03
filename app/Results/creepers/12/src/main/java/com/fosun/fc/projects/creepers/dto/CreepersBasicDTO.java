package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersBasicDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String idNo;

 private  String idType;

 private  String maritalStatus;

 private  String name;

@Temporal(TemporalType.DATE)
 private  Date queryDt;

@Temporal(TemporalType.DATE)
 private  Date rptDt;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersBasicDTO() {
}
public void setName(String name){
    this.name = name;
}


public void setRptDt(Date rptDt){
    this.rptDt = rptDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getName(){
    return this.name;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public String getIdType(){
    return this.idType;
}


public String getMaritalStatus(){
    return this.maritalStatus;
}


public Date getRptDt(){
    return this.rptDt;
}


public void setMaritalStatus(String maritalStatus){
    this.maritalStatus = maritalStatus;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public String getIdNo(){
    return this.idNo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setIdNo(String idNo){
    this.idNo = idNo;
}


public void setIdType(String idType){
    this.idType = idType;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
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