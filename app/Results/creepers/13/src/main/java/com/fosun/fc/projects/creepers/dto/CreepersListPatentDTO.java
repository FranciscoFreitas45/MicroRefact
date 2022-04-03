package com.fosun.fc.projects.creepers.dto;
 public class CreepersListPatentDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String merNo;

 private  String merName;


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getMerName(){
    return merName;
}


public String getMerNo(){
    return merNo;
}


public void setMerName(String merName){
    this.merName = merName;
}


}