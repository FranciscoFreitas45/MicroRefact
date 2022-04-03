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
@Table(name = "T_CREEPERS_MER_PENALTY")
@NamedQuery(name = "TCreepersMerPenalty.findAll", query = "SELECT t FROM TCreepersMerPenalty t")
public class TCreepersMerPenalty {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_PENALTY_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_PENALTY")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_PENALTY_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "CLAIM_AMOUNT", length = 50)
 private  String claimAmount;

@Column(length = 300)
 private  String details;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "PENALTY_AUTHORITY", length = 200)
 private  String penaltyAuthority;

@Column(name = "PENALTY_CONTENT", length = 1000)
 private  String penaltyContent;

@Temporal(TemporalType.DATE)
@Column(name = "PENALTY_DT")
 private  Date penaltyDt;

@Column(name = "PENALTY_NO", length = 100)
 private  String penaltyNo;

@Column(name = "PENALTY_TYPE", length = 20)
 private  String penaltyType;

@Column(name = "SEQ_NO", length = 20)
 private  String seqNo;

@Column(length = 50)
 private  String status;

public TCreepersMerPenalty() {
}
public void setPenaltyType(String penaltyType){
    this.penaltyType = penaltyType;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public void setPenaltyAuthority(String penaltyAuthority){
    this.penaltyAuthority = penaltyAuthority;
}


public String getPenaltyNo(){
    return this.penaltyNo;
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


public String getDetails(){
    return this.details;
}


public String getPenaltyAuthority(){
    return this.penaltyAuthority;
}


public String getSeqNo(){
    return this.seqNo;
}


public String getPenaltyContent(){
    return this.penaltyContent;
}


public void setPenaltyNo(String penaltyNo){
    this.penaltyNo = penaltyNo;
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


public String getPenaltyType(){
    return this.penaltyType;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setPenaltyContent(String penaltyContent){
    this.penaltyContent = penaltyContent;
}


public Date getPenaltyDt(){
    return this.penaltyDt;
}


public void setPenaltyDt(Date penaltyDt){
    this.penaltyDt = penaltyDt;
}


public void setDetails(String details){
    this.details = details;
}


public void setId(long id){
    this.id = id;
}


}