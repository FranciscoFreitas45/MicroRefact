package com.fosun.fc.projects.creepers.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "T_CREEPERS_MER_CHECK")
@NamedQuery(name = "TCreepersMerCheck.findAll", query = "SELECT t FROM TCreepersMerCheck t")
public class TCreepersMerCheck {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_CHECK_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_CHECK")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_CHECK_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 100)
 private  String authority;

@Temporal(TemporalType.DATE)
@Column(name = "CHECK_DT")
 private  Date checkDt;

@Column(name = "CHECK_RESULT", length = 100)
 private  String checkResult;

@Column(name = "CHECK_TYPE", length = 20)
 private  String checkType;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "SEQ_NO", length = 20)
 private  String seqNo;

public TCreepersMerCheck() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getCheckType(){
    return this.checkType;
}


public void setCheckType(String checkType){
    this.checkType = checkType;
}


public void setCheckResult(String checkResult){
    this.checkResult = checkResult;
}


public String getMerNo(){
    return this.merNo;
}


public long getId(){
    return this.id;
}


public void setCheckDt(Date checkDt){
    this.checkDt = checkDt;
}


public String getSeqNo(){
    return this.seqNo;
}


public String getAuthority(){
    return this.authority;
}


public Date getCheckDt(){
    return this.checkDt;
}


public String getCheckResult(){
    return this.checkResult;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setAuthority(String authority){
    this.authority = authority;
}


public void setId(long id){
    this.id = id;
}


}