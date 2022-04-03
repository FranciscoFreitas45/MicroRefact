package com.fosun.fc.projects.creepers.DTO;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersJudgementDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  Date caseDt;

 private  String caseNo;

 private  String caseTitle;

 private  String merNo;

 private  String docId;

 private  String court;

 private  String merName;

public CreepersJudgementDTO() {
}
public Date getCaseDt(){
    return this.caseDt;
}


public String getMerNo(){
    return this.merNo;
}


public void setCaseDt(Date caseDt){
    this.caseDt = caseDt;
}


public String getDocId(){
    return docId;
}


public String getCourt(){
    return court;
}


public String getCaseNo(){
    return this.caseNo;
}


public String getMerName(){
    return merName;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public String getCaseTitle(){
    return this.caseTitle;
}


}