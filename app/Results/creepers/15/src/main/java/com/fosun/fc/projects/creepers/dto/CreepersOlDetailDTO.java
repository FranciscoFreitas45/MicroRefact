package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersOlDetailDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  BigDecimal balance;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String currencyType;

@Temporal(TemporalType.DATE)
 private  Date grantDt;

 private  String grantOrg;

 private  String hlStatus;

 private  BigDecimal loanAmount;

@Temporal(TemporalType.DATE)
 private  Date loanMaturityDt;

 private  String loanType;

 private  BigDecimal olOverdraftSixty;

 private  BigDecimal ollOverdraftNinety;

 private  BigDecimal overdueAmount;

 private  String rptNo;

@Temporal(TemporalType.DATE)
 private  Date statisticalDt;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersOlDetailDTO() {
}
public void setLoanAmount(BigDecimal loanAmount){
    this.loanAmount = loanAmount;
}


public void setLoanMaturityDt(Date loanMaturityDt){
    this.loanMaturityDt = loanMaturityDt;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setCurrencyType(String currencyType){
    this.currencyType = currencyType;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public void setOlOverdraftSixty(BigDecimal olOverdraftSixty){
    this.olOverdraftSixty = olOverdraftSixty;
}


public BigDecimal getOllOverdraftNinety(){
    return this.ollOverdraftNinety;
}


public Date getGrantDt(){
    return this.grantDt;
}


public BigDecimal getOlOverdraftSixty(){
    return this.olOverdraftSixty;
}


public void setGrantOrg(String grantOrg){
    this.grantOrg = grantOrg;
}


public BigDecimal getLoanAmount(){
    return this.loanAmount;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public String getGrantOrg(){
    return this.grantOrg;
}


public Date getLoanMaturityDt(){
    return this.loanMaturityDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getHlStatus(){
    return this.hlStatus;
}


public void setOllOverdraftNinety(BigDecimal ollOverdraftNinety){
    this.ollOverdraftNinety = ollOverdraftNinety;
}


public void setOverdueAmount(BigDecimal overdueAmount){
    this.overdueAmount = overdueAmount;
}


public String getLoanType(){
    return this.loanType;
}


public void setLoanType(String loanType){
    this.loanType = loanType;
}


public void setStatisticalDt(Date statisticalDt){
    this.statisticalDt = statisticalDt;
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


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public void setHlStatus(String hlStatus){
    this.hlStatus = hlStatus;
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


public String getCreatedBy(){
    return this.createdBy;
}


public String getCurrencyType(){
    return this.currencyType;
}


}