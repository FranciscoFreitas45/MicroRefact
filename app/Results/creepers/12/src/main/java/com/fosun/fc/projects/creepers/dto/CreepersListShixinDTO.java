package com.fosun.fc.projects.creepers.dto;
 public class CreepersListShixinDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String merName;

 private  String memo;


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getMerName(){
    return merName;
}


public void setMerName(String merName){
    this.merName = merName;
}


}