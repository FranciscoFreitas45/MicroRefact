package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersAssetHandleDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  BigDecimal assetDisposalAmount;

@Temporal(TemporalType.DATE)
 private  Date assetDisposalDt;

 private  String assetDisposalOrg;

 private  BigDecimal balance;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

@Temporal(TemporalType.DATE)
 private  Date lastBackDt;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersAssetHandleDTO() {
}
public Date getAssetDisposalDt(){
    return this.assetDisposalDt;
}


public void setLastBackDt(Date lastBackDt){
    this.lastBackDt = lastBackDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getAssetDisposalOrg(){
    return this.assetDisposalOrg;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setAssetDisposalAmount(BigDecimal assetDisposalAmount){
    this.assetDisposalAmount = assetDisposalAmount;
}


public Date getLastBackDt(){
    return this.lastBackDt;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public void setAssetDisposalOrg(String assetDisposalOrg){
    this.assetDisposalOrg = assetDisposalOrg;
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


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getAssetDisposalAmount(){
    return this.assetDisposalAmount;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public void setAssetDisposalDt(Date assetDisposalDt){
    this.assetDisposalDt = assetDisposalDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}