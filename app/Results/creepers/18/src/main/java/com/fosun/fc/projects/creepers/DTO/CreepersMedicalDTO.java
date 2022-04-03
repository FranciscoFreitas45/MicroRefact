package com.fosun.fc.projects.creepers.DTO;
 public class CreepersMedicalDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String type;

 private  String key;

 private  String content;


public String getKey(){
    return key;
}


public String getType(){
    return type;
}


public String getContent(){
    return content;
}


}