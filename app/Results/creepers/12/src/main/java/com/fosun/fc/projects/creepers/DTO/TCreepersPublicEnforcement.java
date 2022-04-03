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
public class TCreepersPublicEnforcement {

 private  long serialVersionUID;

 private  Long id;

 private  String caseStatus;

 private  String cause;

 private  String closeWay;

 private  Date closingDt;

 private  Date compulsoryDt;

 private  String enforcementCase;

 private  String enforcementCourt;

 private  String enforcementSubject;

 private  String executedSubject;

 private  BigDecimal executedSubjectAmout;

 private  String memo;

 private  String rptNo;

 private  BigDecimal subjectAmount;

 private  TCreepersAccountBak TCreepersAccountBak;

public TCreepersPublicEnforcement() {
}
public Date getClosingDt(){
    return this.closingDt;
}


public void setExecutedSubjectAmout(BigDecimal executedSubjectAmout){
    this.executedSubjectAmout = executedSubjectAmout;
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


public Long getId(){
    return this.id;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
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


public void setId(Long id){
    this.id = id;
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


public void setMemo(String memo){
    this.memo = memo;
}


public void setEnforcementCase(String enforcementCase){
    this.enforcementCase = enforcementCase;
}


public String getMemo(){
    return this.memo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public void setEnforcementCourt(String enforcementCourt){
    this.enforcementCourt = enforcementCourt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public String getRptNo(){
    return this.rptNo;
}


public String getCaseStatus(){
    return this.caseStatus;
}


public String getEnforcementCourt(){
    return this.enforcementCourt;
}


public String getEnforcementCase(){
    return this.enforcementCase;
}


}