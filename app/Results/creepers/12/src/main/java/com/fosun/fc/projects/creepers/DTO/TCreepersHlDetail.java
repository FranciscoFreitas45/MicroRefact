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
public class TCreepersHlDetail {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal balance;

 private  String currencyType;

 private  Date grantDt;

 private  String grantOrg;

 private  BigDecimal hlOverdraftNinety;

 private  BigDecimal hlOverdraftSixty;

 private  String hlStatus;

 private  BigDecimal loanAmount;

 private  Date loanMaturityDt;

 private  String loanType;

 private  String memo;

 private  BigDecimal overdueAmount;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

public TCreepersHlDetail() {
}
public void setLoanAmount(BigDecimal loanAmount){
    this.loanAmount = loanAmount;
}


public void setLoanMaturityDt(Date loanMaturityDt){
    this.loanMaturityDt = loanMaturityDt;
}


public Long getId(){
    return this.id;
}


public void setCurrencyType(String currencyType){
    this.currencyType = currencyType;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public void setHlOverdraftNinety(BigDecimal hlOverdraftNinety){
    this.hlOverdraftNinety = hlOverdraftNinety;
}


public Date getGrantDt(){
    return this.grantDt;
}


public void setGrantOrg(String grantOrg){
    this.grantOrg = grantOrg;
}


public void setHlOverdraftSixty(BigDecimal hlOverdraftSixty){
    this.hlOverdraftSixty = hlOverdraftSixty;
}


public void setId(Long id){
    this.id = id;
}


public BigDecimal getLoanAmount(){
    return this.loanAmount;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public BigDecimal getHlOverdraftNinety(){
    return this.hlOverdraftNinety;
}


public String getGrantOrg(){
    return this.grantOrg;
}


public Date getLoanMaturityDt(){
    return this.loanMaturityDt;
}


public String getHlStatus(){
    return this.hlStatus;
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


public BigDecimal getHlOverdraftSixty(){
    return this.hlOverdraftSixty;
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


public BigDecimal getBalance(){
    return this.balance;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
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


public String getCurrencyType(){
    return this.currencyType;
}


}