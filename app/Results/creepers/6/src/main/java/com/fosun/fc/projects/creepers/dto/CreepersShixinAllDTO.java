package com.fosun.fc.projects.creepers.dto;
 public class CreepersShixinAllDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String code;

 private  String province;

 private  String goodRecord;

 private  String badRecord;

 private  String disRecord;


public void setName(String name){
    this.name = name;
}


public String getGoodRecord(){
    return goodRecord;
}


public void setDisRecord(String disRecord){
    this.disRecord = disRecord;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public void setBadRecord(String badRecord){
    this.badRecord = badRecord;
}


public String getDisRecord(){
    return disRecord;
}


public void setCode(String code){
    this.code = code;
}


public String getProvince(){
    return province;
}


public void setGoodRecord(String goodRecord){
    this.goodRecord = goodRecord;
}


public String getBadRecord(){
    return badRecord;
}


public String getCode(){
    return code;
}


}