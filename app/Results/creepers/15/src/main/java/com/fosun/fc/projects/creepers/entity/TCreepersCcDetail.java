package com.fosun.fc.projects.creepers.entity;
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
import com.fosun.fc.projects.creepers.Request.TCreepersAccountBakRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersAccountBakRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersAccountBak;
@Entity
@Table(name = "T_CREEPERS_CC_DETAIL")
@NamedQuery(name = "TCreepersCcDetail.findAll", query = "SELECT t FROM TCreepersCcDetail t")
public class TCreepersCcDetail {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_CC_DETAIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_CC_DETAIL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_CC_DETAIL_ID_GENERATOR")
 private  Long id;

@Column(name = "ACCOUNT_TYPE")
 private  String accountType;

@Column(name = "CC_OVERDRAFT_NINETY")
 private  BigDecimal ccOverdraftNinety;

@Column(name = "CC_OVERDRAFT_SIXTY")
 private  BigDecimal ccOverdraftSixty;

@Column(name = "CC_STATUS")
 private  String ccStatus;

@Column(name = "CC_TYPE")
 private  String ccType;

@Column(name = "CREDIT_LIMIT")
 private  String creditLimit;

@Temporal(TemporalType.DATE)
@Column(name = "GRANT_DT")
 private  Date grantDt;

@Column(name = "GRANT_ORG")
 private  String grantOrg;

 private  String memo;

@Column(name = "OVERDRAFT_BALANCE")
 private  BigDecimal overdraftBalance;

@Column(name = "OVERDUE_AMOUNT")
 private  BigDecimal overdueAmount;

@Column(name = "RPT_NO")
 private  String rptNo;

@Temporal(TemporalType.DATE)
@Column(name = "STATISTICAL_DT")
 private  Date statisticalDt;

@Transient
 private  TCreepersAccountBak TCreepersAccountBak;

@Column(name = "id")
 private Long id;

@Transient
 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

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
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
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
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
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