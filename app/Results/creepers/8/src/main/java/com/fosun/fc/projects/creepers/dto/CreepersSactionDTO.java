package com.fosun.fc.projects.creepers.dto;
 public class CreepersSactionDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String type;

 private  String content;

 private  String province;

 private  String modifyDt;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public String getType(){
    return type;
}


public String getContent(){
    return content;
}


public String getProvince(){
    return province;
}


public String getModifyDt(){
    return modifyDt;
}


public void setType(String type){
    this.type = type;
}


public void setModifyDt(String modifyDt){
    this.modifyDt = modifyDt;
}


}