package org.live.DTO;
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
public String getUserGroupName(){
    return userGroupName;
}


public String getName(){
    return name;
}


public String getUserType(){
    return userType;
}


public String getLastLoginIp(){
    return lastLoginIp;
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


public int getIsLock(){
    return isLock;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


}