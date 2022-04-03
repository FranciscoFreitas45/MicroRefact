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
@Table(name = "T_CREEPERS_BASIC")
@NamedQuery(name = "TCreepersBasic.findAll", query = "SELECT t FROM TCreepersBasic t")
public class TCreepersBasic {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_BASIC")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_BASIC_ID_GENERATOR")
 private  Long id;

@Column(name = "ID_NO")
 private  String idNo;

@Column(name = "ID_TYPE")
 private  String idType;

@Column(name = "MARITAL_STATUS")
 private  String maritalStatus;

 private  String memo;

 private  String name;

@Temporal(TemporalType.DATE)
@Column(name = "QUERY_DT")
 private  Date queryDt;

@Temporal(TemporalType.DATE)
@Column(name = "RPT_DT")
 private  Date rptDt;

@Column(name = "RPT_NO")
 private  String rptNo;

@Transient
 private  TCreepersAccountBak TCreepersAccountBak;

@Column(name = "id")
 private Long id;

@Transient
 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

public TCreepersBasic() {
}
public void setName(String name){
    this.name = name;
}


public void setRptDt(Date rptDt){
    this.rptDt = rptDt;
}


public String getName(){
    return this.name;
}


public String getIdType(){
    return this.idType;
}


public String getMaritalStatus(){
    return this.maritalStatus;
}


public Date getRptDt(){
    return this.rptDt;
}


public Long getId(){
    return this.id;
}


public void setMaritalStatus(String maritalStatus){
    this.maritalStatus = maritalStatus;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
}



public void setMemo(String memo){
    this.memo = memo;
}


public String getIdNo(){
    return this.idNo;
}


public String getMemo(){
    return this.memo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public void setIdNo(String idNo){
    this.idNo = idNo;
}


public void setIdType(String idType){
    this.idType = idType;
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