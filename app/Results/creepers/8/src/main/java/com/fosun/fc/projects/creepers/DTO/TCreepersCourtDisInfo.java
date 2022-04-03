package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersCourtDisInfo implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String caseDt;

 private  String caseNo;

 private  String code;

 private  String court;

 private  String duty;

 private  String implementNo;

 private  String implementUnit;

 private  String legalRep;

 private  String memo;

 private  String name;

 private  String performN;

 private  String performY;

 private  String performance;

 private  String province;

 private  String publishDt;

 private  String source;

 private  String specific;

 private  String type;

public TCreepersCourtDisInfo() {
}
public void setSource(String source){
    this.source = source;
}


public String getName(){
    return this.name;
}


public String getPerformY(){
    return this.performY;
}


public String getCaseDt(){
    return this.caseDt;
}


public long getId(){
    return this.id;
}


public String getPerformN(){
    return this.performN;
}


public String getCourt(){
    return this.court;
}


public String getImplementUnit(){
    return this.implementUnit;
}


public String getSpecific(){
    return this.specific;
}


public String getCaseNo(){
    return this.caseNo;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public String getProvince(){
    return this.province;
}


public void setCourt(String court){
    this.court = court;
}


public String getCode(){
    return this.code;
}


public String getLegalRep(){
    return this.legalRep;
}


public String getPerformance(){
    return this.performance;
}


public void setCaseDt(String caseDt){
    this.caseDt = caseDt;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getDuty(){
    return this.duty;
}


public String getMemo(){
    return this.memo;
}


public String getType(){
    return this.type;
}


public String getSource(){
    return this.source;
}


public String getPublishDt(){
    return this.publishDt;
}


public String getImplementNo(){
    return this.implementNo;
}


}