package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersShixinDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String certNo;

 private  String legalRep;

 private  String execCourt;

 private  String province;

 private  String judgementNo;

@Temporal(TemporalType.DATE)
 private  Date caseDt;

 private  String caseNo;

 private  String judgementCourt;

 private  String duty;

 private  String performance;

 private  String behaviorDetails;

@Temporal(TemporalType.DATE)
 private  Date publishDt;


public void setName(String name){
    this.name = name;
}


public void setBehaviorDetails(String behaviorDetails){
    this.behaviorDetails = behaviorDetails;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public Date getCaseDt(){
    return caseDt;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setExecCourt(String execCourt){
    this.execCourt = execCourt;
}


public void setPerformance(String performance){
    this.performance = performance;
}


public void setDuty(String duty){
    this.duty = duty;
}


public String getCaseNo(){
    return caseNo;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public String getProvince(){
    return province;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public String getLegalRep(){
    return legalRep;
}


public String getBehaviorDetails(){
    return behaviorDetails;
}


public String getJudgementCourt(){
    return judgementCourt;
}


public String getPerformance(){
    return performance;
}


public String getJudgementNo(){
    return judgementNo;
}


public String getExecCourt(){
    return execCourt;
}


public void setCaseDt(Date caseDt){
    this.caseDt = caseDt;
}


public String getCertNo(){
    return certNo;
}


public String getDuty(){
    return duty;
}


public Date getPublishDt(){
    return publishDt;
}


public void setPublishDt(Date publishDt){
    this.publishDt = publishDt;
}


public void setJudgementCourt(String judgementCourt){
    this.judgementCourt = judgementCourt;
}


public void setJudgementNo(String judgementNo){
    this.judgementNo = judgementNo;
}


}