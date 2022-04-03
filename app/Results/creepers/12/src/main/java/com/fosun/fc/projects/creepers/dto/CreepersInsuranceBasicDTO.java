package com.fosun.fc.projects.creepers.dto;
 public class CreepersInsuranceBasicDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String certNo;

 private  String workTime;

 private  String password;


public void setName(String name){
    this.name = name;
}


public String getCertNo(){
    return certNo;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public void setWorkTime(String workTime){
    this.workTime = workTime;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public String getWorkTime(){
    return workTime;
}


}