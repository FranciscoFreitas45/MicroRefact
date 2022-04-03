package com.fosun.fc.projects.creepers.dto;
 public class CreepersInsurancePaymentDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String certNo;

 private  String paymentDt;

 private  String paymentBase;

 private  String endowment;

 private  String medical;

 private  String unemployment;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setEndowment(String endowment){
    this.endowment = endowment;
}


public String getPaymentDt(){
    return paymentDt;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public String getEndowment(){
    return endowment;
}


public void setPaymentDt(String paymentDt){
    this.paymentDt = paymentDt;
}


public String getCertNo(){
    return certNo;
}


public String getUnemployment(){
    return unemployment;
}


public void setPaymentBase(String paymentBase){
    this.paymentBase = paymentBase;
}


public String getMedical(){
    return medical;
}


public String getPaymentBase(){
    return paymentBase;
}


public void setMedical(String medical){
    this.medical = medical;
}


public void setUnemployment(String unemployment){
    this.unemployment = unemployment;
}


}