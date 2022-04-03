package com.fosun.fc.projects.creepers.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_OTHER_SHARE_CHANGE")
@NamedQuery(name = "TCreepersOtherShareChange.findAll", query = "SELECT t FROM TCreepersOtherShareChange t")
public class TCreepersOtherShareChange {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_OTHER_SHARE_CHANGE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_OTHER_SHARE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_OTHER_SHARE_CHANGE_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 50)
 private  String assignee;

@Column(length = 500)
 private  String detail;

@Column(name = "EQUITY_AMOUNT", length = 50)
 private  String equityAmount;

@Column(name = "EXEC_COURT", length = 100)
 private  String execCourt;

@Column(name = "EXEC_MAN", length = 100)
 private  String execMan;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "SEQ_NO", length = 5)
 private  String seqNo;

public TCreepersOtherShareChange() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getEquityAmount(){
    return this.equityAmount;
}


public void setDetail(String detail){
    this.detail = detail;
}


public String getDetail(){
    return this.detail;
}


public String getExecCourt(){
    return this.execCourt;
}


public String getMerNo(){
    return this.merNo;
}


public long getId(){
    return this.id;
}


public String getAssignee(){
    return this.assignee;
}


public String getSeqNo(){
    return this.seqNo;
}


public void setExecCourt(String execCourt){
    this.execCourt = execCourt;
}


public void setExecMan(String execMan){
    this.execMan = execMan;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public String getExecMan(){
    return this.execMan;
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public void setId(long id){
    this.id = id;
}


public void setEquityAmount(String equityAmount){
    this.equityAmount = equityAmount;
}


}