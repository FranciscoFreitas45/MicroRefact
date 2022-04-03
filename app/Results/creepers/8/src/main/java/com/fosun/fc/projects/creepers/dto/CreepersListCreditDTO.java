package com.fosun.fc.projects.creepers.dto;
 public class CreepersListCreditDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String userCode;

 private  String password;

 private  String messageCode;

 private  String imageUrl;

 private  String htmlUrl;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setPassword(String password){
    this.password = password;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getPassword(){
    return password;
}


public String getImageUrl(){
    return imageUrl;
}


public String getHtmlUrl(){
    return htmlUrl;
}


public void setHtmlUrl(String htmlUrl){
    this.htmlUrl = htmlUrl;
}


public String getMessageCode(){
    return messageCode;
}


public void setMessageCode(String messageCode){
    this.messageCode = messageCode;
}


public String getUserCode(){
    return userCode;
}


}