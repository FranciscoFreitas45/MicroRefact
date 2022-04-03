package com.fosun.fc.projects.creepers.dto;
 public class CreepersListInsuranceDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String userCode;

 private  String password;


public void setPassword(String password){
    this.password = password;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getPassword(){
    return password;
}


public String getUserCode(){
    return userCode;
}


}