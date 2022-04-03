package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersPublicSanctionDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String docNo;

@Temporal(TemporalType.DATE)
 private  Date punishDt;

 private  BigDecimal punishmentAmount;

 private  String punishmentContent;

@Temporal(TemporalType.DATE)
 private  Date punishmentIssueDt;

 private  String punishmentOrg;

 private  String reconsiderationFlag;

 private  String reconsiderationRslt;

 private  String rptNo;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersPublicSanctionDTO() {
}
public void setPunishmentContent(String punishmentContent){
    this.punishmentContent = punishmentContent;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setPunishDt(Date punishDt){
    this.punishDt = punishDt;
}


public BigDecimal getPunishmentAmount(){
    return this.punishmentAmount;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public void setPunishmentAmount(BigDecimal punishmentAmount){
    this.punishmentAmount = punishmentAmount;
}


public Date getPunishmentIssueDt(){
    return this.punishmentIssueDt;
}


public void setPunishmentIssueDt(Date punishmentIssueDt){
    this.punishmentIssueDt = punishmentIssueDt;
}


public void setPunishmentOrg(String punishmentOrg){
    this.punishmentOrg = punishmentOrg;
}


public String getReconsiderationFlag(){
    return this.reconsiderationFlag;
}


public void setReconsiderationFlag(String reconsiderationFlag){
    this.reconsiderationFlag = reconsiderationFlag;
}


public String getPunishmentOrg(){
    return this.punishmentOrg;
}


public String getPunishmentContent(){
    return this.punishmentContent;
}


public void setReconsiderationRslt(String reconsiderationRslt){
    this.reconsiderationRslt = reconsiderationRslt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getDocNo(){
    return this.docNo;
}


public Date getPunishDt(){
    return this.punishDt;
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


public void setDocNo(String docNo){
    this.docNo = docNo;
}


public String getReconsiderationRslt(){
    return this.reconsiderationRslt;
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


}