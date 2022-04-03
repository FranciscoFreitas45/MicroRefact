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
@Table(name = "T_CREEPERS_GUARANTEE")
@NamedQuery(name = "TCreepersGuarantee.findAll", query = "SELECT t FROM TCreepersGuarantee t")
public class TCreepersGuarantee {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_GUARANTEE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_GUARANTEE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_GUARANTEE_ID_GENERATOR")
 private  Long id;

@Column(name = "GUARANTEE_CONTRACT_AMOUNT")
 private  BigDecimal guaranteeContractAmount;

@Column(name = "GUARANTEED_PRINCIPAL_BALANCE")
 private  BigDecimal guaranteedPrincipalBalance;

@Column(name = "GUARANTEET_AMOUNT")
 private  BigDecimal guaranteetAmount;

@Column(name = "INSURED_ID_NO")
 private  String insuredIdNo;

@Column(name = "INSURED_ID_TYPE")
 private  String insuredIdType;

@Column(name = "INSURED_NAME")
 private  String insuredName;

@Column(name = "LOAN_ORG")
 private  String loanOrg;

 private  String memo;

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
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
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
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
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