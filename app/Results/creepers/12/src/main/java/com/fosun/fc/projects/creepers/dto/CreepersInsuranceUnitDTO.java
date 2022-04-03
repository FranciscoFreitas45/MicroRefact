package com.fosun.fc.projects.creepers.dto;
 public class CreepersInsuranceUnitDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String certNo;

 private  String unit;

 private  String period;


public void setName(String name){
    this.name = name;
}


public String getCertNo(){
    return certNo;
}


public String getName(){
    return name;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setUnit(String unit){
    this.unit = unit;
}


public String getUnit(){
    return unit;
}


public void setPeriod(String period){
    this.period = period;
}


public String getPeriod(){
    return period;
}


}