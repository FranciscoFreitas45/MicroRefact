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

public TCreepersPublicIsp() {
}
public void setServiceType(String serviceType){
    this.serviceType = serviceType;
}


public Long getId(){
    return this.id;
}


public Date getJournalEntry(){
    return this.journalEntry;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public void setTeleOperators(String teleOperators){
    this.teleOperators = teleOperators;
}


public BigDecimal getArrearsAmount(){
    return this.arrearsAmount;
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


public void setBizOperateDt(Date bizOperateDt){
    this.bizOperateDt = bizOperateDt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public String getTeleOperators(){
    return this.teleOperators;
}


public void setId(Long id){
    this.id = id;
}


public String getRptNo(){
    return this.rptNo;
}


public Date getBizOperateDt(){
    return this.bizOperateDt;
}


public void setJournalEntry(Date journalEntry){
    this.journalEntry = journalEntry;
}


public void setArrearsAmount(BigDecimal arrearsAmount){
    this.arrearsAmount = arrearsAmount;
}


public String getServiceType(){
    return this.serviceType;
}


}