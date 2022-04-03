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

 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

public TCreepersCompensatory() {
}
public void setLastBackDt(BigDecimal lastBackDt){
    this.lastBackDt = lastBackDt;
}


public void setCompensationAmount(BigDecimal compensationAmount){
    this.compensationAmount = compensationAmount;
}


public void setCompensatoryOrg(String compensatoryOrg){
    this.compensatoryOrg = compensatoryOrg;
}


public Date getLastCompensatoryDt(){
    return this.lastCompensatoryDt;
}


public void setLastCompensatoryDt(Date lastCompensatoryDt){
    this.lastCompensatoryDt = lastCompensatoryDt;
}


public Long getId(){
    return this.id;
}


public BigDecimal getLastBackDt(){
    return this.lastBackDt;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
}



public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getBalance(){
    return this.balance;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public void setId(Long id){
    this.id = id;
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


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


}