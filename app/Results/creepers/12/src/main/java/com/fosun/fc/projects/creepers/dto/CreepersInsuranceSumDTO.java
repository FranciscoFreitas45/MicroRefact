package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
public class CreepersInsuranceSumDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String certNo;

 private  String endDt;

 private  BigDecimal months;

 private  String endowmentSum;

 private  String endowmentSumPrivate;


public void setName(String name){
    this.name = name;
}


public String getCertNo(){
    return certNo;
}


public String getName(){
    return name;
}


public String getEndDt(){
    return endDt;
}


public String getEndowmentSumPrivate(){
    return endowmentSumPrivate;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setEndDt(String endDt){
    this.endDt = endDt;
}


public void setMonths(BigDecimal months){
    this.months = months;
}


public String getEndowmentSum(){
    return endowmentSum;
}


public void setEndowmentSumPrivate(String endowmentSumPrivate){
    this.endowmentSumPrivate = endowmentSumPrivate;
}


public BigDecimal getMonths(){
    return months;
}


public void setEndowmentSum(String endowmentSum){
    this.endowmentSum = endowmentSum;
}


}