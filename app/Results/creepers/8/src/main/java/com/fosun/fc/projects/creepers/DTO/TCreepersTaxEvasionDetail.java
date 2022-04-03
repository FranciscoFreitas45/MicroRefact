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
public class TCreepersTaxEvasionDetail implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String addr;

 private  String caseNature;

 private  String certName;

 private  String code;

 private  String court;

 private  String financialCertName;

 private  String financialName;

 private  String financialSex;

 private  String illegalFacts;

 private  String interInfo;

 private  String legalRep;

 private  String legalSex;

 private  String memo;

 private  String name;

 private  String province;

 private  String publishLevel;

 private  String punishInfo;

 private  String source;

 private  String taxNo;

 private  String type;

public TCreepersTaxEvasionDetail() {
}
public void setInterInfo(String interInfo){
    this.interInfo = interInfo;
}


public String getName(){
    return this.name;
}


public void setProvince(String province){
    this.province = province;
}


public String getIllegalFacts(){
    return this.illegalFacts;
}


public long getId(){
    return this.id;
}


public String getCertName(){
    return this.certName;
}


public String getCourt(){
    return this.court;
}


public String getCaseNature(){
    return this.caseNature;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public String getFinancialSex(){
    return this.financialSex;
}


public String getProvince(){
    return this.province;
}


public String getFinancialCertName(){
    return this.financialCertName;
}


public String getLegalSex(){
    return this.legalSex;
}


public String getAddr(){
    return this.addr;
}


public void setLegalSex(String legalSex){
    this.legalSex = legalSex;
}


public String getCode(){
    return this.code;
}


public String getLegalRep(){
    return this.legalRep;
}


public String getPublishLevel(){
    return this.publishLevel;
}


public String getInterInfo(){
    return this.interInfo;
}


public void setCaseNature(String caseNature){
    this.caseNature = caseNature;
}


public String getPunishInfo(){
    return this.punishInfo;
}


public String getFinancialName(){
    return this.financialName;
}


public String getMemo(){
    return this.memo;
}


public void setAddr(String addr){
    this.addr = addr;
}


public String getType(){
    return this.type;
}


public String getSource(){
    return this.source;
}


public String getTaxNo(){
    return this.taxNo;
}


}