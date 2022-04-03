package com.gs.bean;
 import java.util.Date;
public class UserRole {

 private  String userRoleId;

 private  String userId;

 private  String roleId;

 private  Date urcreatedTime;


public void setRoleId(String roleId){
    this.roleId = roleId;
}


public void setUserRoleId(String userRoleId){
    this.userRoleId = userRoleId;
}


public void setUrcreatedTime(Date urcreatedTime){
    this.urcreatedTime = urcreatedTime;
}


public String getRoleId(){
    return roleId;
}


public String getUserRoleId(){
    return userRoleId;
}


public Date getUrcreatedTime(){
    return urcreatedTime;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}