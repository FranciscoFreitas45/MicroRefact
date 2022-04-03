package com.dtdhehe.ptulife.vo;
 public class UserRegistVO {

 private  String userName;

 private  String userPwd;

 private  String userSex;

 private  String userStatus;

 private  String orgName;

 private  String nickName;

 private  String email;


public void setUserSex(String userSex){
    this.userSex = userSex;
}


public String getOrgName(){
    return orgName;
}


public String getUserSex(){
    return userSex;
}


public String getNickName(){
    return nickName;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


public void setUserPwd(String userPwd){
    this.userPwd = userPwd;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


public String getUserStatus(){
    return userStatus;
}


public void setEmail(String email){
    this.email = email;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getEmail(){
    return email;
}


public String getUserName(){
    return userName;
}


@Override
public String toString(){
    return "UserRegistVO{" + "userName='" + userName + '\'' + ", userPwd='" + userPwd + '\'' + ", userSex='" + userSex + '\'' + ", userStatus='" + userStatus + '\'' + ", orgName='" + orgName + '\'' + '}';
}


public String getUserPwd(){
    return userPwd;
}


public void setUserStatus(String userStatus){
    this.userStatus = userStatus;
}


}