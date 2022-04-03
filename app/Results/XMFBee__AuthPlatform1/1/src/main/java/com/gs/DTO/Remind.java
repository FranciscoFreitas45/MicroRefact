package com.gs.DTO;
 public class Remind {

 private  String remindId;

 private  String remindDes;

 private  String remindUser;

 private  User user;


public String getRemindId(){
    return remindId;
}


public User getUser(){
    return user;
}


public String getRemindUser(){
    return remindUser;
}


public String getRemindDes(){
    return remindDes;
}


@Override
public String toString(){
    return "Remind{" + "remindId='" + remindId + '\'' + ", remindDes='" + remindDes + '\'' + ", remindUser='" + remindUser + '\'' + ", user=" + user + '}';
}


public void setUser(User user){
    this.user = user;
}


}