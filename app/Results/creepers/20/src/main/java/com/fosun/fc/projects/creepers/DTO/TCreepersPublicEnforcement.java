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

 private Long id;

public TCreepersPublicEnforcement() {
}
public Date getClosingDt(){
    return this.closingDt;
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


public String getCause(){
    return this.cause;
}


public String getExecutedSubject(){
    return this.executedSubject;
}


public String getEnforcementSubject(){
    return this.enforcementSubject;
}


public String getCloseWay(){
    return this.closeWay;
}


public Date getCompulsoryDt(){
    return this.compulsoryDt;
}


public String getMemo(){
    return this.memo;
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