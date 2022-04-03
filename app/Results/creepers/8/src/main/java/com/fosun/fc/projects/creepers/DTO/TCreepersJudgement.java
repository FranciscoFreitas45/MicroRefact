package com.fosun.fc.projects.creepers.DTO;
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
public class TCreepersJudgement {

 private  long serialVersionUID;

 private  long id;

 private  Date caseDt;

 private  String caseNo;

 private  String caseTitle;

 private  String merNo;

 private  String merName;

 private  String docId;

 private  String court;

 private  String memo;

public TCreepersJudgement() {
}
public Date getCaseDt(){
    return this.caseDt;
}


public String getMerNo(){
    return this.merNo;
}


public long getId(){
    return this.id;
}


public String getDocId(){
    return docId;
}


public String getCourt(){
    return court;
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


public void setCourt(String court){
    this.court = court;
}


public String getCaseTitle(){
    return this.caseTitle;
}


}