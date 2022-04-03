package com.gs.bean;
 import com.gs.Interface.User;
public class Remind {

 private  String remindId;

 private  String remindDes;

 private  String remindUser;

 private  User user;


public void setRemindUser(String remindUser){
    this.remindUser = remindUser;
}


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


public void setRemindDes(String remindDes){
    this.remindDes = remindDes;
}


@Override
public String toString(){
    return "Remind{" + "remindId='" + remindId + '\'' + ", remindDes='" + remindDes + '\'' + ", remindUser='" + remindUser + '\'' + ", user=" + user + '}';
}


public void setRemindId(String remindId){
    this.remindId = remindId;
}


public void setUser(User user){
    this.user = user;
}


}