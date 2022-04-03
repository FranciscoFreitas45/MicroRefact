package org.live.sys.vo;
 import java.util.Date;
public class UserVo {

 public  String id;

 private  String username;

 private  String name;

 private  String userType;

 private  Date registerTime;

 private  int isLock;

 private  Date lastLoginTime;

 private  String lastLoginIp;

 private  String userGroupName;

 private  String groupId;

public UserVo() {
}
public void setName(String name){
    this.name = name;
}


public String getUserGroupName(){
    return userGroupName;
}


public void setGroupId(String groupId){
    this.groupId = groupId;
}


public String getName(){
    return name;
}


public String getUserType(){
    return userType;
}


public void setRegisterTime(Date registerTime){
    this.registerTime = registerTime;
}


public void setUserGroupName(String userGroupName){
    this.userGroupName = userGroupName;
}


public void setUsername(String username){
    this.username = username;
}


public void setIsLock(int isLock){
    this.isLock = isLock;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
}


public String getId(){
    return id;
}


public String getGroupId(){
    return groupId;
}


public Date getRegisterTime(){
    return registerTime;
}


public String getUsername(){
    return username;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public void setUserType(String userType){
    this.userType = userType;
}


public int getIsLock(){
    return isLock;
}


public void setId(String id){
    this.id = id;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


}