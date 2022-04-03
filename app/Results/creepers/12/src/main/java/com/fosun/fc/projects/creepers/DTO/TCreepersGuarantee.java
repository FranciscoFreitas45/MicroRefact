package com.fosun.fc.projects.creepers.DTO;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class TCreepersGuarantee {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal guaranteeContractAmount;

 private  BigDecimal guaranteedPrincipalBalance;

 private  BigDecimal guaranteetAmount;

 private  String insuredIdNo;

 private  String insuredIdType;

 private  String insuredName;

 private  String loanOrg;

 private  String memo;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

public TCreepersGuarantee() {
}
public String getInsuredName(){
    return this.insuredName;
}


public void setLoanOrg(String loanOrg){
    this.loanOrg = loanOrg;
}


public void setInsuredIdNo(String insuredIdNo){
    this.insuredIdNo = insuredIdNo;
}


public void setGuaranteeContractAmount(BigDecimal guaranteeContractAmount){
    this.guaranteeContractAmount = guaranteeContractAmount;
}


public Long getId(){
    return this.id;
}


public BigDecimal getGuaranteetAmount(){
    return this.guaranteetAmount;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public String getInsuredIdNo(){
    return this.insuredIdNo;
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


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public BigDecimal getGuaranteeContractAmount(){
    return this.guaranteeContractAmount;
}


public void setGuaranteedPrincipalBalance(BigDecimal guaranteedPrincipalBalance){
    this.guaranteedPrincipalBalance = guaranteedPrincipalBalance;
}


public void setGuaranteetAmount(BigDecimal guaranteetAmount){
    this.guaranteetAmount = guaranteetAmount;
}


public void setInsuredName(String insuredName){
    this.insuredName = insuredName;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public void setId(Long id){
    this.id = id;
}


public BigDecimal getGuaranteedPrincipalBalance(){
    return this.guaranteedPrincipalBalance;
}


public String getRptNo(){
    return this.rptNo;
}


public String getLoanOrg(){
    return this.loanOrg;
}


}