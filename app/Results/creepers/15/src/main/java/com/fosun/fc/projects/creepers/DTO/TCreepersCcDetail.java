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
public class TCreepersCcDetail {

 private  long serialVersionUID;

 private  Long id;

 private  String accountType;

 private  BigDecimal ccOverdraftNinety;

 private  BigDecimal ccOverdraftSixty;

 private  String ccStatus;

 private  String ccType;

 private  String creditLimit;

 private  Date grantDt;

 private  String grantOrg;

 private  String memo;

 private  BigDecimal overdraftBalance;

 private  BigDecimal overdueAmount;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersCcDetail() {
}
public String getCcStatus(){
    return this.ccStatus;
}


public Long getId(){
    return this.id;
}


public BigDecimal getCcOverdraftNinety(){
    return this.ccOverdraftNinety;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
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


public void setId(Long id){
    this.id = id;
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


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public BigDecimal getOverdraftBalance(){
    return this.overdraftBalance;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
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


public String getCreditLimit(){
    return this.creditLimit;
}


public void setOverdraftBalance(BigDecimal overdraftBalance){
    this.overdraftBalance = overdraftBalance;
}


}