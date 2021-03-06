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
@Table(name = "T_CREEPERS_JUDGEMENT")
@NamedQuery(name = "TCreepersJudgement.findAll", query = "SELECT t FROM TCreepersJudgement t")
public class TCreepersJudgement {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_JUDGEMENT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_JUDGEMENT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_JUDGEMENT_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Temporal(TemporalType.DATE)
@Column(name = "CASE_DT")
 private  Date caseDt;

@Column(name = "CASE_NO", length = 300)
 private  String caseNo;

@Column(name = "CASE_TITLE", length = 500)
 private  String caseTitle;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "MER_NAME", length = 1000)
 private  String merName;

@Column(name = "DOC_ID", length = 100)
 private  String docId;

@Column(name = "COURT", length = 100)
 private  String court;

 private  String memo;

public TCreepersJudgement() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public Date getCaseDt(){
    return this.caseDt;
}


public void setCaseTitle(String caseTitle){
    this.caseTitle = caseTitle;
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


public void setCaseDt(Date caseDt){
    this.caseDt = caseDt;
}


public String getDocId(){
    return docId;
}


public void setDocId(String docId){
    this.docId = docId;
}


public String getCourt(){
    return court;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getCaseNo(){
    return this.caseNo;
}


public String getMerName(){
    return merName;
}


public void setId(long id){
    this.id = id;
}


public void setCourt(String court){
    this.court = court;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public String getCaseTitle(){
    return this.caseTitle;
}


}