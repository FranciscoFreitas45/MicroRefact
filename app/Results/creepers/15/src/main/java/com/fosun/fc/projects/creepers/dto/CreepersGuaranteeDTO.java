package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersGuaranteeDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  BigDecimal guaranteeContractAmount;

 private  BigDecimal guaranteedPrincipalBalance;

 private  BigDecimal guaranteetAmount;

 private  String insuredIdNo;

 private  String insuredIdType;

 private  String insuredName;

 private  String loanOrg;

 private  String rptNo;

@Temporal(TemporalType.DATE)
 private  Date statisticalDt;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersGuaranteeDTO() {
}
public String getInsuredName(){
    return this.insuredName;
}


public void setLoanOrg(String loanOrg){
    this.loanOrg = loanOrg;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setInsuredIdNo(String insuredIdNo){
    this.insuredIdNo = insuredIdNo;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public BigDecimal getGuaranteetAmount(){
    return this.guaranteetAmount;
}


public String getInsuredIdNo(){
    return this.insuredIdNo;
}


public BigDecimal getGuaranteeContractAmount(){
    return this.guaranteeContractAmount;
}


public void setGuaranteedPrincipalBalance(BigDecimal guaranteedPrincipalBalance){
    this.guaranteedPrincipalBalance = guaranteedPrincipalBalance;
}


public void setInsuredName(String insuredName){
    this.insuredName = insuredName;
}


public BigDecimal getGuaranteedPrincipalBalance(){
    return this.guaranteedPrincipalBalance;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public void setGuaranteeContractAmount(BigDecimal guaranteeContractAmount){
    this.guaranteeContractAmount = guaranteeContractAmount;
}


public void setInsuredIdType(String insuredIdType){
    this.insuredIdType = insuredIdType;
}


public String getInsuredIdType(){
    return this.insuredIdType;
}


public void setStatisticalDt(Date statisticalDt){
    this.statisticalDt = statisticalDt;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setGuaranteetAmount(BigDecimal guaranteetAmount){
    this.guaranteetAmount = guaranteetAmount;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public String getRptNo(){
    return this.rptNo;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public String getLoanOrg(){
    return this.loanOrg;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}