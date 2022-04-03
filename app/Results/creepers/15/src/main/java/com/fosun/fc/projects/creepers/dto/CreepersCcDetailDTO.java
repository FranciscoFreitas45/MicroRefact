package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersCcDetailDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String accountType;

 private  BigDecimal ccOverdraftNinety;

 private  BigDecimal ccOverdraftSixty;

 private  String ccStatus;

 private  String ccType;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String creditLimit;

@Temporal(TemporalType.DATE)
 private  Date grantDt;

 private  String grantOrg;

 private  BigDecimal overdraftBalance;

 private  BigDecimal overdueAmount;

 private  String rptNo;

@Temporal(TemporalType.DATE)
 private  Date statisticalDt;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersCcDetailDTO() {
}
public String getCcStatus(){
    return this.ccStatus;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public BigDecimal getCcOverdraftNinety(){
    return this.ccOverdraftNinety;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public String getCcType(){
    return this.ccType;
}


public void setCcOverdraftSixty(BigDecimal ccOverdraftSixty){
    this.ccOverdraftSixty = ccOverdraftSixty;
}


public Date getGrantDt(){
    return this.grantDt;
}


public void setCcStatus(String ccStatus){
    this.ccStatus = ccStatus;
}


public void setGrantOrg(String grantOrg){
    this.grantOrg = grantOrg;
}


public void setCreditLimit(String creditLimit){
    this.creditLimit = creditLimit;
}


public void setCcOverdraftNinety(BigDecimal ccOverdraftNinety){
    this.ccOverdraftNinety = ccOverdraftNinety;
}


public void setAccountType(String accountType){
    this.accountType = accountType;
}


public BigDecimal getCcOverdraftSixty(){
    return this.ccOverdraftSixty;
}


public String getGrantOrg(){
    return this.grantOrg;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public void setOverdueAmount(BigDecimal overdueAmount){
    this.overdueAmount = overdueAmount;
}


public String getAccountType(){
    return this.accountType;
}


public void setCcType(String ccType){
    this.ccType = ccType;
}


public void setStatisticalDt(Date statisticalDt){
    this.statisticalDt = statisticalDt;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public BigDecimal getOverdraftBalance(){
    return this.overdraftBalance;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public BigDecimal getOverdueAmount(){
    return this.overdueAmount;
}


public void setGrantDt(Date grantDt){
    this.grantDt = grantDt;
}


public String getRptNo(){
    return this.rptNo;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreditLimit(){
    return this.creditLimit;
}


public String getCreatedBy(){
    return this.createdBy;
}


public void setOverdraftBalance(BigDecimal overdraftBalance){
    this.overdraftBalance = overdraftBalance;
}


}