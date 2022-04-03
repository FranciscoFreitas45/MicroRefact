package com.fosun.fc.projects.creepers.dto;
 public class CreepersCourtDisInfoDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String type;

 private  String code;

 private  String province;

 private  String source;

 private  String caseNo;

 private  String legalRep;

 private  String court;

 private  String duty;

 private  String performance;

 private  String specific;

 private  String publishDt;

 private  String implementNo;

 private  String caseDt;

 private  String implementUnit;

 private  String performY;

 private  String performN;


public void setName(String name){
    this.name = name;
}


public void setSource(String source){
    this.source = source;
}


public String getName(){
    return name;
}


public String getPerformY(){
    return performY;
}


public void setProvince(String province){
    this.province = province;
}


public void setSpecific(String specific){
    this.specific = specific;
}


public String getCaseDt(){
    return caseDt;
}


public void setPerformN(String performN){
    this.performN = performN;
}


public String getPerformN(){
    return performN;
}


public void setPerformance(String performance){
    this.performance = performance;
}


public String getCourt(){
    return court;
}


public void setDuty(String duty){
    this.duty = duty;
}


public String getSpecific(){
    return specific;
}


public String getImplementUnit(){
    return implementUnit;
}


public void setImplementUnit(String implementUnit){
    this.implementUnit = implementUnit;
}


public String getCaseNo(){
    return caseNo;
}


public void setImplementNo(String implementNo){
    this.implementNo = implementNo;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public String getProvince(){
    return province;
}


public void setCourt(String court){
    this.court = court;
}


public void setCaseNo(String caseNo){
    this.caseNo = caseNo;
}


public String getCode(){
    return code;
}


public void setPerformY(String performY){
    this.performY = performY;
}


public String getLegalRep(){
    return legalRep;
}


public String getPerformance(){
    return performance;
}


public void setCode(String code){
    this.code = code;
}


public void setCaseDt(String caseDt){
    this.caseDt = caseDt;
}


public void setType(String type){
    this.type = type;
}


public String getDuty(){
    return duty;
}


public String getType(){
    return type;
}


public String getSource(){
    return source;
}


public String getPublishDt(){
    return publishDt;
}


public void setPublishDt(String publishDt){
    this.publishDt = publishDt;
}


public String getImplementNo(){
    return implementNo;
}


}