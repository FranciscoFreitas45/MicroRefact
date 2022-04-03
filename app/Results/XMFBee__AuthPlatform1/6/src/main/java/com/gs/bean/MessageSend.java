package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Checkin;
import com.gs.Interface.User;
public class MessageSend {

 private  String messageId;

 private  String userId;

 private  String sendMsg;

 private  Date sendTime;

 private  Date sendCreatedTime;

 private  String companyId;

 private  Checkin checkin;

 private  Company company;

 private  User user;


public User getUser(){
    return user;
}


public void setMessageId(String messageId){
    this.messageId = messageId;
}


public Date getSendCreatedTime(){
    return sendCreatedTime;
}


public Company getCompany(){
    return company;
}


public String getSendMsg(){
    return sendMsg;
}


public String getMessageId(){
    return messageId;
}


public String getCompanyId(){
    return companyId;
}


public void setCheckin(Checkin checkin){
    this.checkin = checkin;
}


public void setSendMsg(String sendMsg){
    this.sendMsg = sendMsg;
}


public void setSendTime(Date sendTime){
    this.sendTime = sendTime;
}


public void setSendCreatedTime(Date sendCreatedTime){
    this.sendCreatedTime = sendCreatedTime;
}


public Checkin getCheckin(){
    return checkin;
}


public void setUser(User user){
    this.user = user;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public Date getSendTime(){
    return sendTime;
}


}