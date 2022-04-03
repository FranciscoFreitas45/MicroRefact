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
public class TCreepersShixin {

 private  long serialVersionUID;

 private  long id;

 private  String behaviorDetails;

 private  Date caseDt;

 private  String caseNo;

 private  String certNo;

 private  String duty;

 private  String execCourt;

 private  String judgementCourt;

 private  String judgementNo;

 private  String legalRep;

 private  String memo;

 private  String name;

 private  String performance;

 private  String province;

 private  Date publishDt;

public TCreepersShixin() {
}
public void setBehaviorDetails(String behaviorDetails){
    this.behaviorDetails = behaviorDetails;
}


public String getName(){
    return this.name;
}


public Date getCaseDt(){
    return this.caseDt;
}


public long getId(){
    return this.id;
}


public void setPerformance(String performance){
    this.performance = performance;
}


public String getCaseNo(){
    return this.caseNo;
}


public String getProvince(){
    return this.province;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public String getLegalRep(){
    return this.legalRep;
}


public String getBehaviorDetails(){
    return this.behaviorDetails;
}


public String getJudgementCourt(){
    return this.judgementCourt;
}


public String getPerformance(){
    return this.performance;
}


public String getJudgementNo(){
    return this.judgementNo;
}


public String getExecCourt(){
    return this.execCourt;
}


public String getCertNo(){
    return this.certNo;
}


public String getDuty(){
    return this.duty;
}


public String getMemo(){
    return this.memo;
}


public Date getPublishDt(){
    return this.publishDt;
}


public void setJudgementCourt(String judgementCourt){
    this.judgementCourt = judgementCourt;
}


}