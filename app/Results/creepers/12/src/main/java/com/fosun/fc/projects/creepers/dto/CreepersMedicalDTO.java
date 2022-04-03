package com.fosun.fc.projects.creepers.dto;
 public class CreepersMedicalDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String type;

 private  String key;

 private  String content;


public String getKey(){
    return key;
}


public void setContent(String content){
    this.content = content;
}


public String getType(){
    return type;
}


public String getContent(){
    return content;
}


public void setType(String type){
    this.type = type;
}


public void setKey(String key){
    this.key = key;
}


}