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
public class TCreepersGuarantee {

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal guaranteeContractAmount;

 private  BigDecimal guaranteedPrincipalBalance;

 private  BigDecimal guaranteetAmount;

 private  String insuredIdNo;

 private  String insuredIdType;

 private  String insuredName;

 private  String loanOrg;

 private  String memo;

 private  String rptNo;

 private  Date statisticalDt;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersGuarantee() {
}
public String getInsuredName(){
    return this.insuredName;
}


public Long getId(){
    return this.id;
}


public BigDecimal getGuaranteetAmount(){
    return this.guaranteetAmount;
}


public String getInsuredIdNo(){
    return this.insuredIdNo;
}


public String getInsuredIdType(){
    return this.insuredIdType;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getGuaranteeContractAmount(){
    return this.guaranteeContractAmount;
}


public Date getStatisticalDt(){
    return this.statisticalDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public BigDecimal getGuaranteedPrincipalBalance(){
    return this.guaranteedPrincipalBalance;
}


public String getRptNo(){
    return this.rptNo;
}


public String getLoanOrg(){
    return this.loanOrg;
}


}