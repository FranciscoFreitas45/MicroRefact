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
public class TCreepersHlDetail {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal balance;

 private  String currencyType;

 private  Date grantDt;

 private  String grantOrg;

 private  BigDecimal hlOverdraftNinety;

 private  BigDecimal hlOverdraftSixty;

 private  String hlStatus;

 private  BigDecimal loanAmount;

 private  Date loanMaturityDt;

 private  String loanType;

 private  String memo;

 private  BigDecimal overdueAmount;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersHlDetail() {
}
public Long getId(){
    return this.id;
}


public Date getGrantDt(){
    return this.grantDt;
}


public BigDecimal getLoanAmount(){
    return this.loanAmount;
}


public BigDecimal getHlOverdraftNinety(){
    return this.hlOverdraftNinety;
}


public String getGrantOrg(){
    return this.grantOrg;
}


public Date getLoanMaturityDt(){
    return this.loanMaturityDt;
}


public String getHlStatus(){
    return this.hlStatus;
}


public String getLoanType(){
    return this.loanType;
}


public BigDecimal getHlOverdraftSixty(){
    return this.hlOverdraftSixty;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getBalance(){
    return this.balance;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public BigDecimal getOverdueAmount(){
    return this.overdueAmount;
}


public String getRptNo(){
    return this.rptNo;
}


public String getCurrencyType(){
    return this.currencyType;
}


}