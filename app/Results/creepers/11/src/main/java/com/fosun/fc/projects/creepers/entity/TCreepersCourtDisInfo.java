package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_COURT_DIS_INFO")
@NamedQuery(name = "TCreepersCourtDisInfo.findAll", query = "SELECT t FROM TCreepersCourtDisInfo t")
public class TCreepersCourtDisInfo implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_COURT_DIS_INFO_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_COURT_DIS_INFO")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_COURT_DIS_INFO_ID_GENERATOR")
 private  long id;

@Column(name = "CASE_DT")
 private  String caseDt;

@Column(name = "CASE_NO")
 private  String caseNo;

 private  String code;

 private  String court;

 private  String duty;

@Column(name = "IMPLEMENT_NO")
 private  String implementNo;

@Column(name = "IMPLEMENT_UNIT")
 private  String implementUnit;

@Column(name = "LEGAL_REP")
 private  String legalRep;

 private  String memo;

 private  String name;

@Column(name = "PERFORM_N")
 private  String performN;

@Column(name = "PERFORM_Y")
 private  String performY;

 private  String performance;

 private  String province;

@Column(name = "PUBLISH_DT")
 private  String publishDt;

 private  String source;

 private  String specific;

 private  String type;

public TCreepersCourtDisInfo() {
}
public void setName(String name){
    this.name = name;
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


public void setProvince(String province){
    this.province = province;
}


public String getCaseDt(){
    return this.caseDt;
}


public void setSpecific(String specific){
    this.specific = specific;
}


public long getId(){
    return this.id;
}


public void setPerformN(String performN){
    this.performN = performN;
}


public String getPerformN(){
    return this.performN;
}


public void setPerformance(String performance){
    this.performance = performance;
}


public String getCourt(){
    return this.court;
}


public void setDuty(String duty){
    this.duty = duty;
}


public String getImplementUnit(){
    return this.implementUnit;
}


public String getSpecific(){
    return this.specific;
}


public void setImplementUnit(String implementUnit){
    this.implementUnit = implementUnit;
}


public String getCaseNo(){
    return this.caseNo;
}


public void setImplementNo(String implementNo){
    this.implementNo = implementNo;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public String getProvince(){
    return this.province;
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


public String getCode(){
    return this.code;
}


public void setPerformY(String performY){
    this.performY = performY;
}


public String getLegalRep(){
    return this.legalRep;
}


public String getPerformance(){
    return this.performance;
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


public void setPublishDt(String publishDt){
    this.publishDt = publishDt;
}


public String getImplementNo(){
    return this.implementNo;
}


}