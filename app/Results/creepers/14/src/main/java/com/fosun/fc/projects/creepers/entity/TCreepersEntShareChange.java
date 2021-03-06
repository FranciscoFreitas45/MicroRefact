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
@Table(name = "T_CREEPERS_ENT_SHARE_CHANGE")
@NamedQuery(name = "TCreepersEntShareChange.findAll", query = "SELECT t FROM TCreepersEntShareChange t")
public class TCreepersEntShareChange {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_SHARE_CHANGE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_SHARE_CHANGE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_SHARE_CHANGE_ID_GENERATOR")
 private  long id;

@Column(name = "CHANGE_CONTENT")
 private  String changeContent;

@Column(name = "SEQ_NO")
 private  String seqNo;

@Temporal(TemporalType.DATE)
@Column(name = "CHANGE_DT")
 private  Date changeDt;

@Column(name = "CHANGE_NEW")
 private  String changeNew;

@Column(name = "CHANGE_OLD")
 private  String changeOld;

 private  String memo;

@Column(name = "MER_NAME")
 private  String merName;

@Column(name = "MER_NO")
 private  String merNo;

public TCreepersEntShareChange() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getChangeContent(){
    return this.changeContent;
}


public String getMerNo(){
    return this.merNo;
}


public void setMerName(String merName){
    this.merName = merName;
}


public long getId(){
    return this.id;
}


public String getSeqNo(){
    return seqNo;
}


public String getChangeOld(){
    return this.changeOld;
}


public void setChangeOld(String changeOld){
    this.changeOld = changeOld;
}


public Date getChangeDt(){
    return this.changeDt;
}


public String getChangeNew(){
    return this.changeNew;
}


public void setChangeNew(String changeNew){
    this.changeNew = changeNew;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setChangeDt(Date changeDt){
    this.changeDt = changeDt;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setChangeContent(String changeContent){
    this.changeContent = changeContent;
}


public String getMerName(){
    return this.merName;
}


public void setId(long id){
    this.id = id;
}


}