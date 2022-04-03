package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersPublicEnforcementDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String caseStatus;

 private  String cause;

 private  String closeWay;

@Temporal(TemporalType.DATE)
 private  Date closingDt;

@Temporal(TemporalType.DATE)
 private  Date compulsoryDt;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

 private  String enforcementCase;

 private  String enforcementCourt;

 private  String enforcementSubject;

 private  String executedSubject;

 private  BigDecimal executedSubjectAmout;

 private  String rptNo;

 private  BigDecimal subjectAmount;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

public CreepersPublicEnforcementDTO() {
}
public Date getClosingDt(){
    return this.closingDt;
}


public void setExecutedSubjectAmout(BigDecimal executedSubjectAmout){
    this.executedSubjectAmout = executedSubjectAmout;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setEnforcementSubject(String enforcementSubject){
    this.enforcementSubject = enforcementSubject;
}


public BigDecimal getExecutedSubjectAmout(){
    return this.executedSubjectAmout;
}


public BigDecimal getSubjectAmount(){
    return this.subjectAmount;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public String getCause(){
    return this.cause;
}


public void setExecutedSubject(String executedSubject){
    this.executedSubject = executedSubject;
}


public String getExecutedSubject(){
    return this.executedSubject;
}


public String getEnforcementSubject(){
    return this.enforcementSubject;
}


public void setCompulsoryDt(Date compulsoryDt){
    this.compulsoryDt = compulsoryDt;
}


public void setSubjectAmount(BigDecimal subjectAmount){
    this.subjectAmount = subjectAmount;
}


public void setClosingDt(Date closingDt){
    this.closingDt = closingDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public void setCause(String cause){
    this.cause = cause;
}


public void setCloseWay(String closeWay){
    this.closeWay = closeWay;
}


public void setCaseStatus(String caseStatus){
    this.caseStatus = caseStatus;
}


public String getCloseWay(){
    return this.closeWay;
}


public Date getCompulsoryDt(){
    return this.compulsoryDt;
}


public void setEnforcementCase(String enforcementCase){
    this.enforcementCase = enforcementCase;
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


public void setEnforcementCourt(String enforcementCourt){
    this.enforcementCourt = enforcementCourt;
}


public String getRptNo(){
    return this.rptNo;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public String getCaseStatus(){
    return this.caseStatus;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


public String getEnforcementCourt(){
    return this.enforcementCourt;
}


public String getEnforcementCase(){
    return this.enforcementCase;
}


}