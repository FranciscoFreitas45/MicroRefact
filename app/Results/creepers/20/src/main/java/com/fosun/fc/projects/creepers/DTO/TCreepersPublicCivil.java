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

public TCreepersPublicCivil() {
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


public String getSubjectAction(){
    return this.subjectAction;
}


public String getCaseNo(){
    return this.caseNo;
}


public Date getFilingDt(){
    return this.filingDt;
}


public String getClosedWay(){
    return this.closedWay;
}


public Date getDecisionDt(){
    return this.decisionDt;
}


public String getMemo(){
    return this.memo;
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