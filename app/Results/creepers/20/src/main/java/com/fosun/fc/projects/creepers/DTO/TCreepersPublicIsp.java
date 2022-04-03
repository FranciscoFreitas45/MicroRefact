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
public class TCreepersPublicIsp {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal arrearsAmount;

 private  Date bizOperateDt;

 private  Date journalEntry;

 private  String memo;

 private  String rptNo;

 private  String serviceType;

 private  String teleOperators;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersPublicIsp() {
}
public Long getId(){
    return this.id;
}


public Date getJournalEntry(){
    return this.journalEntry;
}


public BigDecimal getArrearsAmount(){
    return this.arrearsAmount;
}


public String getMemo(){
    return this.memo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public String getTeleOperators(){
    return this.teleOperators;
}


public String getRptNo(){
    return this.rptNo;
}


public Date getBizOperateDt(){
    return this.bizOperateDt;
}


public String getServiceType(){
    return this.serviceType;
}


}