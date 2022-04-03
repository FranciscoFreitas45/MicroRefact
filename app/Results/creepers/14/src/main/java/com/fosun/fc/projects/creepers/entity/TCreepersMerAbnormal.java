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
@Table(name = "T_CREEPERS_MER_ABNORMAL")
@NamedQuery(name = "TCreepersMerAbnormal.findAll", query = "SELECT t FROM TCreepersMerAbnormal t")
public class TCreepersMerAbnormal {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_ABNORMAL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_ABNORMAL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_ABNORMAL_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Temporal(TemporalType.DATE)
@Column(name = "ABNORMAL_DT")
 private  Date abnormalDt;

@Column(name = "ABNORMAL_REASON", length = 200)
 private  String abnormalReason;

@Column(length = 100)
 private  String authority;

@Column(name = "CLAIM_AMOUNT", length = 50)
 private  String claimAmount;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Temporal(TemporalType.DATE)
@Column(name = "REMOVE_DT")
 private  Date removeDt;

@Column(name = "REMOVE_REASON", length = 200)
 private  String removeReason;

@Column(name = "SEQ_NO", length = 20)
 private  String seqNo;

@Column(length = 50)
 private  String status;

public TCreepersMerAbnormal() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public Date getRemoveDt(){
    return this.removeDt;
}


public void setRemoveReason(String removeReason){
    this.removeReason = removeReason;
}


public String getMerNo(){
    return this.merNo;
}


public long getId(){
    return this.id;
}


public void setClaimAmount(String claimAmount){
    this.claimAmount = claimAmount;
}


public String getSeqNo(){
    return this.seqNo;
}


public void setAbnormalDt(Date abnormalDt){
    this.abnormalDt = abnormalDt;
}


public String getAuthority(){
    return this.authority;
}


public String getStatus(){
    return this.status;
}


public String getClaimAmount(){
    return this.claimAmount;
}


public void setStatus(String status){
    this.status = status;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setAuthority(String authority){
    this.authority = authority;
}


public void setRemoveDt(Date removeDt){
    this.removeDt = removeDt;
}


public void setId(long id){
    this.id = id;
}


public String getRemoveReason(){
    return this.removeReason;
}


public Date getAbnormalDt(){
    return this.abnormalDt;
}


public void setAbnormalReason(String abnormalReason){
    this.abnormalReason = abnormalReason;
}


public String getAbnormalReason(){
    return this.abnormalReason;
}


}