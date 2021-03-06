package com.fosun.fc.projects.creepers.entity;
 import java.math.BigDecimal;
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
@Entity
@Table(name = "T_CREEPERS_GENERAL")
@NamedQuery(name = "TCreepersGeneral.findAll", query = "SELECT t FROM TCreepersGeneral t")
public class TCreepersGeneral {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_GENERAL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_GENERAL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_GENERAL_ID_GENERATOR")
 private  Long id;

@Column(name = "ASSET_DISPOSAL_NO")
 private  BigDecimal assetDisposalNo;

@Column(name = "CC_NO")
 private  BigDecimal ccNo;

@Column(name = "GUARANTEE_CC_NO")
 private  BigDecimal guaranteeCcNo;

@Column(name = "GUARANTEE_HOUSING_LOAN_NO")
 private  BigDecimal guaranteeHousingLoanNo;

@Column(name = "GUARANTEE_OTHER_LOAN_NO")
 private  BigDecimal guaranteeOtherLoanNo;

@Column(name = "GUARANTOR_COMPENSATION_NO")
 private  BigDecimal guarantorCompensationNo;

@Column(name = "HOUSING_LOAN_NO")
 private  BigDecimal housingLoanNo;

 private  String memo;

@Column(name = "NINETY_CC_NO")
 private  BigDecimal ninetyCcNo;

@Column(name = "NINETY_HOUSING_LOAN_NO")
 private  BigDecimal ninetyHousingLoanNo;

@Column(name = "NINETY_OTHER_LOAN_NO")
 private  BigDecimal ninetyOtherLoanNo;

@Column(name = "OTHER_LOAN_NO")
 private  BigDecimal otherLoanNo;

@Column(name = "OUTSTANDING_CC_NO")
 private  BigDecimal outstandingCcNo;

@Column(name = "OUTSTANDING_HOUSING_LOAN_NO")
 private  BigDecimal outstandingHousingLoanNo;

@Column(name = "OUTSTANDING_OTHER_LOAN_NO")
 private  BigDecimal outstandingOtherLoanNo;

@Column(name = "OVERDUE_CC_NO")
 private  BigDecimal overdueCcNo;

@Column(name = "OVERDUE_HOUSING_LOAN_NO")
 private  BigDecimal overdueHousingLoanNo;

@Column(name = "OVERDUE_OTHER_LOAN_NO")
 private  BigDecimal overdueOtherLoanNo;

@Column(name = "RPT_NO")
 private  String rptNo;

@ManyToOne
@JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
 private  TCreepersAccountBak TCreepersAccountBak;

public TCreepersGeneral() {
}
public void setGuaranteeOtherLoanNo(BigDecimal guaranteeOtherLoanNo){
    this.guaranteeOtherLoanNo = guaranteeOtherLoanNo;
}


public BigDecimal getOutstandingHousingLoanNo(){
    return this.outstandingHousingLoanNo;
}


public void setNinetyHousingLoanNo(BigDecimal ninetyHousingLoanNo){
    this.ninetyHousingLoanNo = ninetyHousingLoanNo;
}


public Long getId(){
    return this.id;
}


public BigDecimal getCcNo(){
    return this.ccNo;
}


public BigDecimal getGuaranteeOtherLoanNo(){
    return this.guaranteeOtherLoanNo;
}


public BigDecimal getOutstandingCcNo(){
    return this.outstandingCcNo;
}


public void setCcNo(BigDecimal ccNo){
    this.ccNo = ccNo;
}


public BigDecimal getHousingLoanNo(){
    return this.housingLoanNo;
}


public void setOverdueHousingLoanNo(BigDecimal overdueHousingLoanNo){
    this.overdueHousingLoanNo = overdueHousingLoanNo;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public void setNinetyCcNo(BigDecimal ninetyCcNo){
    this.ninetyCcNo = ninetyCcNo;
}


public BigDecimal getOverdueHousingLoanNo(){
    return this.overdueHousingLoanNo;
}


public void setOutstandingHousingLoanNo(BigDecimal outstandingHousingLoanNo){
    this.outstandingHousingLoanNo = outstandingHousingLoanNo;
}


public void setAssetDisposalNo(BigDecimal assetDisposalNo){
    this.assetDisposalNo = assetDisposalNo;
}


public void setGuarantorCompensationNo(BigDecimal guarantorCompensationNo){
    this.guarantorCompensationNo = guarantorCompensationNo;
}


public void setGuaranteeHousingLoanNo(BigDecimal guaranteeHousingLoanNo){
    this.guaranteeHousingLoanNo = guaranteeHousingLoanNo;
}


public BigDecimal getOutstandingOtherLoanNo(){
    return this.outstandingOtherLoanNo;
}


public void setId(Long id){
    this.id = id;
}


public void setOutstandingCcNo(BigDecimal outstandingCcNo){
    this.outstandingCcNo = outstandingCcNo;
}


public BigDecimal getGuaranteeCcNo(){
    return this.guaranteeCcNo;
}


public BigDecimal getOtherLoanNo(){
    return this.otherLoanNo;
}


public void setGuaranteeCcNo(BigDecimal guaranteeCcNo){
    this.guaranteeCcNo = guaranteeCcNo;
}


public void setHousingLoanNo(BigDecimal housingLoanNo){
    this.housingLoanNo = housingLoanNo;
}


public void setOutstandingOtherLoanNo(BigDecimal outstandingOtherLoanNo){
    this.outstandingOtherLoanNo = outstandingOtherLoanNo;
}


public BigDecimal getGuaranteeHousingLoanNo(){
    return this.guaranteeHousingLoanNo;
}


public BigDecimal getOverdueCcNo(){
    return this.overdueCcNo;
}


public void setOtherLoanNo(BigDecimal otherLoanNo){
    this.otherLoanNo = otherLoanNo;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setNinetyOtherLoanNo(BigDecimal ninetyOtherLoanNo){
    this.ninetyOtherLoanNo = ninetyOtherLoanNo;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getOverdueOtherLoanNo(){
    return this.overdueOtherLoanNo;
}


public void setOverdueOtherLoanNo(BigDecimal overdueOtherLoanNo){
    this.overdueOtherLoanNo = overdueOtherLoanNo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public BigDecimal getGuarantorCompensationNo(){
    return this.guarantorCompensationNo;
}


public void setOverdueCcNo(BigDecimal overdueCcNo){
    this.overdueCcNo = overdueCcNo;
}


public BigDecimal getAssetDisposalNo(){
    return this.assetDisposalNo;
}


public BigDecimal getNinetyOtherLoanNo(){
    return this.ninetyOtherLoanNo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public BigDecimal getNinetyHousingLoanNo(){
    return this.ninetyHousingLoanNo;
}


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getNinetyCcNo(){
    return this.ninetyCcNo;
}


}