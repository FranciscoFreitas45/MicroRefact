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
import com.fosun.fc.projects.creepers.Request.TCreepersAccountBakRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersAccountBakRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersAccountBak;
public class TCreepersPublicCivil {

 private  long serialVersionUID;

 private  Long id;

 private  String caseNo;

 private  String causeAction;

 private  String closedWay;

 private  Date decisionDt;

 private  String decisionRslt;

 private  String filingCourt;

 private  Date filingDt;

 private  String memo;

 private  String rptNo;

 private  BigDecimal sbujectAmount;

 private  String subjectAction;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

public TCreepersPublicCivil() {
}
public void setDecisionRslt(String decisionRslt){
    this.decisionRslt = decisionRslt;
}


public String getDecisionRslt(){
    return this.decisionRslt;
}


public BigDecimal getSbujectAmount(){
    return this.sbujectAmount;
}


public Long getId(){
    return this.id;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
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


public void setId(Long id){
    this.id = id;
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


public void setCauseAction(String causeAction){
    this.causeAction = causeAction;
}


public void setClosedWay(String closedWay){
    this.closedWay = closedWay;
}


public Date getDecisionDt(){
    return this.decisionDt;
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


public void setSubjectAction(String subjectAction){
    this.subjectAction = subjectAction;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
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


}