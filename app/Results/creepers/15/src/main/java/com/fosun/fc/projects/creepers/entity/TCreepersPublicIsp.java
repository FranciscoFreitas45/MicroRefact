package com.fosun.fc.projects.creepers.entity;
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
@Entity
@Table(name = "T_CREEPERS_PUBLIC_ISP")
@NamedQuery(name = "TCreepersPublicIsp.findAll", query = "SELECT t FROM TCreepersPublicIsp t")
public class TCreepersPublicIsp {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_PUBLIC_ISP_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_ISP")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_ISP_ID_GENERATOR")
 private  Long id;

@Column(name = "ARREARS_AMOUNT")
 private  BigDecimal arrearsAmount;

@Temporal(TemporalType.DATE)
@Column(name = "BIZ_OPERATE_DT")
 private  Date bizOperateDt;

@Temporal(TemporalType.DATE)
@Column(name = "JOURNAL_ENTRY")
 private  Date journalEntry;

 private  String memo;

@Column(name = "RPT_NO")
 private  String rptNo;

@Column(name = "SERVICE_TYPE")
 private  String serviceType;

@Column(name = "TELE_OPERATORS")
 private  String teleOperators;

@Transient
 private  TCreepersAccountBak TCreepersAccountBak;

@Column(name = "id")
 private Long id;

@Transient
 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

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
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
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
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
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