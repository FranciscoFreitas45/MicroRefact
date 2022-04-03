package com.fosun.fc.projects.creepers.DTO;
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

 private  Date caseDt;

 private  String caseNo;

 private  String judgementCourt;

 private  String duty;

 private  String performance;

 private  String behaviorDetails;

 private  Date publishDt;


public void setBehaviorDetails(String behaviorDetails){
    this.behaviorDetails = behaviorDetails;
}


public String getName(){
    return name;
}


public Date getCaseDt(){
    return caseDt;
}


public void setExecCourt(String execCourt){
    this.execCourt = execCourt;
}


public void setDuty(String duty){
    this.duty = duty;
}


public String getCaseNo(){
    return caseNo;
}


public String getProvince(){
    return province;
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


public String getCertNo(){
    return certNo;
}


public String getDuty(){
    return duty;
}


public Date getPublishDt(){
    return publishDt;
}


public void setJudgementCourt(String judgementCourt){
    this.judgementCourt = judgementCourt;
}


}