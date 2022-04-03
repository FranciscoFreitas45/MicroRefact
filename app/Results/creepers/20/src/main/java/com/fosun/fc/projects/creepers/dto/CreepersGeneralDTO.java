package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersGeneralDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  BigDecimal assetDisposalNo;

 private  BigDecimal ccNo;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  BigDecimal guaranteeCcNo;

 private  BigDecimal guaranteeHousingLoanNo;

 private  BigDecimal guaranteeOtherLoanNo;

 private  BigDecimal guarantorCompensationNo;

 private  BigDecimal housingLoanNo;

 private  BigDecimal ninetyCcNo;

 private  BigDecimal ninetyHousingLoanNo;

 private  BigDecimal ninetyOtherLoanNo;

 private  BigDecimal otherLoanNo;

 private  BigDecimal outstandingCcNo;

 private  BigDecimal outstandingHousingLoanNo;

 private  BigDecimal outstandingOtherLoanNo;

 private  BigDecimal overdueCcNo;

 private  BigDecimal overdueHousingLoanNo;

 private  BigDecimal overdueOtherLoanNo;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersGeneralDTO() {
}
public void setGuaranteeOtherLoanNo(BigDecimal guaranteeOtherLoanNo){
    this.guaranteeOtherLoanNo = guaranteeOtherLoanNo;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public BigDecimal getOutstandingHousingLoanNo(){
    return this.outstandingHousingLoanNo;
}


public void setNinetyHousingLoanNo(BigDecimal ninetyHousingLoanNo){
    this.ninetyHousingLoanNo = ninetyHousingLoanNo;
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


public Date getUpdatedDt(){
    return this.updatedDt;
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


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
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


public void setNinetyOtherLoanNo(BigDecimal ninetyOtherLoanNo){
    this.ninetyOtherLoanNo = ninetyOtherLoanNo;
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


public Date getCreatedDt(){
    return this.createdDt;
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


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public BigDecimal getNinetyOtherLoanNo(){
    return this.ninetyOtherLoanNo;
}


public BigDecimal getNinetyHousingLoanNo(){
    return this.ninetyHousingLoanNo;
}


public String getRptNo(){
    return this.rptNo;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public BigDecimal getNinetyCcNo(){
    return this.ninetyCcNo;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}