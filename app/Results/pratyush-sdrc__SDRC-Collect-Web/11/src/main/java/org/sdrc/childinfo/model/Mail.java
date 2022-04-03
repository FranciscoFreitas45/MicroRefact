package org.sdrc.childinfo.model;
 public class Mail {

 private  StringBuffer fromUserName;

 private  String toUserName;

 private  String toEmailId;

 private  String cc;

 private  StringBuffer subject;

 private  StringBuffer msg;


public StringBuffer getSubject(){
    return subject;
}


public void setSubject(StringBuffer subject){
    this.subject = subject;
}


public StringBuffer getMsg(){
    return msg;
}


public StringBuffer getFromUserName(){
    return fromUserName;
}


public void setToEmailId(String toEmailId){
    this.toEmailId = toEmailId;
}


public void setCc(String cc){
    this.cc = cc;
}


public void setFromUserName(StringBuffer fromUserName){
    this.fromUserName = fromUserName;
}


public String getCc(){
    return cc;
}


public void setMsg(StringBuffer msg){
    this.msg = msg;
}


public String getToUserName(){
    return toUserName;
}


public void setToUserName(String toUserName){
    this.toUserName = toUserName;
}


public String getToEmailId(){
    return toEmailId;
}


}