package com.fosun.fc.projects.creepers.entity;
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
@Table(name = "T_CREEPERS_QUERY_LOG")
@NamedQuery(name = "TCreepersQueryLog.findAll", query = "SELECT t FROM TCreepersQueryLog t")
public class TCreepersQueryLog {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_QUERY_LOG_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_QUERY_LOG")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_QUERY_LOG_ID_GENERATOR")
 private  Long id;

 private  String memo;

@Column(name = "QUERY_BY")
 private  String queryBy;

@Temporal(TemporalType.DATE)
@Column(name = "QUERY_DT")
 private  Date queryDt;

@Column(name = "QUERY_REASON")
 private  String queryReason;

@Column(name = "RPT_NO")
 private  String rptNo;

@Transient
 private  TCreepersAccountBak TCreepersAccountBak;

@Column(name = "id")
 private Long id;

@Transient
 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

public TCreepersQueryLog() {
}
public Long getId(){
    return this.id;
}


public String getQueryReason(){
    return this.queryReason;
}


public void setQueryReason(String queryReason){
    this.queryReason = queryReason;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
}



public String getQueryBy(){
    return this.queryBy;
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


public void setQueryBy(String queryBy){
    this.queryBy = queryBy;
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


public Date getQueryDt(){
    return this.queryDt;
}


public void setQueryDt(Date queryDt){
    this.queryDt = queryDt;
}


}