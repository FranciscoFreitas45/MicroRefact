package com.fosun.fc.projects.creepers.dto;
 public class CreepersCourtCorpBondsDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String code;

 private  String province;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public void setCode(String code){
    this.code = code;
}


public String getProvince(){
    return province;
}


public String getCode(){
    return code;
}


}