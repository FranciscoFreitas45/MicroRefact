package com.fosun.fc.projects.creepers.dto;
 public class CreepersCourtDishonestDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String code;

 private  String province;

 private  String source;


public void setName(String name){
    this.name = name;
}


public void setSource(String source){
    this.source = source;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public String getSource(){
    return source;
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