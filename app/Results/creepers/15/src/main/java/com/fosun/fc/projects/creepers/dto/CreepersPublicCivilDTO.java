package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersPublicCivilDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String caseNo;

 private  String causeAction;

 private  String closedWay;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

@Temporal(TemporalType.DATE)
 private  Date decisionDt;

 private  String decisionRslt;

 private  String filingCourt;

@Temporal(TemporalType.DATE)
 private  Date filingDt;

 private  String rptNo;

 private  BigDecimal sbujectAmount;

 private  String subjectAction;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersPublicCivilDTO() {
}
public void setDecisionRslt(String decisionRslt){
    this.decisionRslt = decisionRslt;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public String getDecisionRslt(){
    return this.decisionRslt;
}


public BigDecimal getSbujectAmount(){
    return this.sbujectAmount;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public String getSubjectAction(){
    return this.subjectAction;
}


public String getCaseNo(){
    return this.caseNo;
}


public void setFilingDt(Date filingDt){
    this.filingDt = filingDt;
}


public void setFilingCourt(String filingCourt){
    this.filingCourt = filingCourt;
}


public Date getFilingDt(){
    return this.filingDt;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public void setDecisionDt(Date decisionDt){
    this.decisionDt = decisionDt;
}


public String getClosedWay(){
    return this.closedWay;
}


public void setSbujectAmount(BigDecimal sbujectAmount){
    this.sbujectAmount = sbujectAmount;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public void setCauseAction(String causeAction){
    this.causeAction = causeAction;
}


public void setClosedWay(String closedWay){
    this.closedWay = closedWay;
}


public Date getDecisionDt(){
    return this.decisionDt;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public void setSubjectAction(String subjectAction){
    this.subjectAction = subjectAction;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getFilingCourt(){
    return this.filingCourt;
}


public String getRptNo(){
    return this.rptNo;
}


public String getCauseAction(){
    return this.causeAction;
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