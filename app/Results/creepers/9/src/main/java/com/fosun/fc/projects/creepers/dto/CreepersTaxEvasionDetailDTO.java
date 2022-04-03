package com.fosun.fc.projects.creepers.dto;
 public class CreepersTaxEvasionDetailDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String source;

 private  String type;

 private  String province;

 private  String court;

 private  String name;

 private  String taxNo;

 private  String code;

 private  String addr;

 private  String legalRep;

 private  String legalSex;

 private  String certName;

 private  String financialName;

 private  String financialSex;

 private  String financialCertName;

 private  String interInfo;

 private  String caseNature;

 private  String illegalFacts;

 private  String punishInfo;

 private  String publishLevel;


public void setName(String name){
    this.name = name;
}


public void setSource(String source){
    this.source = source;
}


public void setInterInfo(String interInfo){
    this.interInfo = interInfo;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public void setFinancialCertName(String financialCertName){
    this.financialCertName = financialCertName;
}


public String getIllegalFacts(){
    return illegalFacts;
}


public void setIllegalFacts(String illegalFacts){
    this.illegalFacts = illegalFacts;
}


public String getCourt(){
    return court;
}


public String getCertName(){
    return certName;
}


public void setFinancialSex(String financialSex){
    this.financialSex = financialSex;
}


public String getCaseNature(){
    return caseNature;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public void setFinancialName(String financialName){
    this.financialName = financialName;
}


public String getFinancialSex(){
    return financialSex;
}


public String getProvince(){
    return province;
}


public String getLegalSex(){
    return legalSex;
}


public String getFinancialCertName(){
    return financialCertName;
}


public void setCourt(String court){
    this.court = court;
}


public String getAddr(){
    return addr;
}


public void setLegalSex(String legalSex){
    this.legalSex = legalSex;
}


public String getCode(){
    return code;
}


public void setCertName(String certName){
    this.certName = certName;
}


public String getLegalRep(){
    return legalRep;
}


public String getPublishLevel(){
    return publishLevel;
}


public String getInterInfo(){
    return interInfo;
}


public void setPublishLevel(String publishLevel){
    this.publishLevel = publishLevel;
}


public void setCaseNature(String caseNature){
    this.caseNature = caseNature;
}


public void setCode(String code){
    this.code = code;
}


public String getPunishInfo(){
    return punishInfo;
}


public void setType(String type){
    this.type = type;
}


public String getFinancialName(){
    return financialName;
}


public void setPunishInfo(String punishInfo){
    this.punishInfo = punishInfo;
}


public void setAddr(String addr){
    this.addr = addr;
}


public String getType(){
    return type;
}


public String getSource(){
    return source;
}


public void setTaxNo(String taxNo){
    this.taxNo = taxNo;
}


public String getTaxNo(){
    return taxNo;
}


}