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
public class TCreepersCompensatory {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal balance;

 private  BigDecimal compensationAmount;

 private  String compensatoryOrg;

 private  BigDecimal lastBackDt;

 private  Date lastCompensatoryDt;

 private  String memo;

 private  String rptNo;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersCompensatory() {
}
public Date getLastCompensatoryDt(){
    return this.lastCompensatoryDt;
}


public Long getId(){
    return this.id;
}


public BigDecimal getLastBackDt(){
    return this.lastBackDt;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getBalance(){
    return this.balance;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getCompensationAmount(){
    return this.compensationAmount;
}


public String getCompensatoryOrg(){
    return this.compensatoryOrg;
}


}