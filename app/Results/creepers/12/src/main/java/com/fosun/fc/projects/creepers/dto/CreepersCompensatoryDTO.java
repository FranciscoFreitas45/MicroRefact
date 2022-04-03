package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersCompensatoryDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  BigDecimal balance;

 private  BigDecimal compensationAmount;

 private  String compensatoryOrg;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  BigDecimal lastBackDt;

@Temporal(TemporalType.DATE)
 private  Date lastCompensatoryDt;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersCompensatoryDTO() {
}
public void setLastBackDt(BigDecimal lastBackDt){
    this.lastBackDt = lastBackDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setCompensationAmount(BigDecimal compensationAmount){
    this.compensationAmount = compensationAmount;
}


public void setCompensatoryOrg(String compensatoryOrg){
    this.compensatoryOrg = compensatoryOrg;
}


public Date getLastCompensatoryDt(){
    return this.lastCompensatoryDt;
}


public void setLastCompensatoryDt(Date lastCompensatoryDt){
    this.lastCompensatoryDt = lastCompensatoryDt;
}


public BigDecimal getLastBackDt(){
    return this.lastBackDt;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public BigDecimal getBalance(){
    return this.balance;
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


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getCompensationAmount(){
    return this.compensationAmount;
}


public String getCompensatoryOrg(){
    return this.compensatoryOrg;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}