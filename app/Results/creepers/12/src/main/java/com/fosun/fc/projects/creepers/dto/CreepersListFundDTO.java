package com.fosun.fc.projects.creepers.dto;
 public class CreepersListFundDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String userCode;

 private  String memo;

 private  String password;


public void setMemo(String memo){
    this.memo = memo;
}


public void setPassword(String password){
    this.password = password;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getMemo(){
    return memo;
}


public String getPassword(){
    return password;
}


public String getUserCode(){
    return userCode;
}


}