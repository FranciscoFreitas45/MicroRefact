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
public class TCreepersCcDetail {

 private  long serialVersionUID;

 private  Long id;

 private  String accountType;

 private  BigDecimal ccOverdraftNinety;

 private  BigDecimal ccOverdraftSixty;

 private  String ccStatus;

 private  String ccType;

 private  String creditLimit;

 private  Date grantDt;

 private  String grantOrg;

 private  String memo;

 private  BigDecimal overdraftBalance;

 private  BigDecimal overdueAmount;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersCcDetail() {
}
public String getCcStatus(){
    return this.ccStatus;
}


public Long getId(){
    return this.id;
}


public BigDecimal getCcOverdraftNinety(){
    return this.ccOverdraftNinety;
}


public String getCcType(){
    return this.ccType;
}


public Date getGrantDt(){
    return this.grantDt;
}


public BigDecimal getCcOverdraftSixty(){
    return this.ccOverdraftSixty;
}


public String getGrantOrg(){
    return this.grantOrg;
}


public String getAccountType(){
    return this.accountType;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getOverdraftBalance(){
    return this.overdraftBalance;
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


public String getCreditLimit(){
    return this.creditLimit;
}


}