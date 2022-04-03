package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersPublicIspDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  BigDecimal arrearsAmount;

@Temporal(TemporalType.DATE)
 private  Date bizOperateDt;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

@Temporal(TemporalType.DATE)
 private  Date journalEntry;

 private  String rptNo;

 private  String serviceType;

 private  String teleOperators;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersPublicIspDTO() {
}
public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setServiceType(String serviceType){
    this.serviceType = serviceType;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public Date getJournalEntry(){
    return this.journalEntry;
}


public void setTeleOperators(String teleOperators){
    this.teleOperators = teleOperators;
}


public BigDecimal getArrearsAmount(){
    return this.arrearsAmount;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public void setBizOperateDt(Date bizOperateDt){
    this.bizOperateDt = bizOperateDt;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getTeleOperators(){
    return this.teleOperators;
}


public String getRptNo(){
    return this.rptNo;
}


public Date getBizOperateDt(){
    return this.bizOperateDt;
}


public void setJournalEntry(Date journalEntry){
    this.journalEntry = journalEntry;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setArrearsAmount(BigDecimal arrearsAmount){
    this.arrearsAmount = arrearsAmount;
}


public String getServiceType(){
    return this.serviceType;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}