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
@Table(name = "T_CREEPERS_PUBLIC_ENFORCEMENT")
@NamedQuery(name = "TCreepersPublicEnforcement.findAll", query = "SELECT t FROM TCreepersPublicEnforcement t")
public class TCreepersPublicEnforcement {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_PUBLIC_ENFORCEMENT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_EF")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_ENFORCEMENT_ID_GENERATOR")
 private  Long id;

@Column(name = "CASE_STATUS")
 private  String caseStatus;

 private  String cause;

@Column(name = "CLOSE_WAY")
 private  String closeWay;

@Temporal(TemporalType.DATE)
@Column(name = "CLOSING_DT")
 private  Date closingDt;

@Temporal(TemporalType.DATE)
@Column(name = "COMPULSORY_DT")
 private  Date compulsoryDt;

@Column(name = "ENFORCEMENT_CASE")
 private  String enforcementCase;

@Column(name = "ENFORCEMENT_COURT")
 private  String enforcementCourt;

@Column(name = "ENFORCEMENT_SUBJECT")
 private  String enforcementSubject;

@Column(name = "EXECUTED_SUBJECT")
 private  String executedSubject;

@Column(name = "EXECUTED_SUBJECT_AMOUT")
 private  BigDecimal executedSubjectAmout;

 private  String memo;

@Column(name = "RPT_NO")
 private  String rptNo;

@Column(name = "SUBJECT_AMOUNT")
 private  BigDecimal subjectAmount;

@Transient
 private  TCreepersAccountBak TCreepersAccountBak;

@Column(name = "id")
 private Long id;

@Transient
 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

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
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
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
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
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