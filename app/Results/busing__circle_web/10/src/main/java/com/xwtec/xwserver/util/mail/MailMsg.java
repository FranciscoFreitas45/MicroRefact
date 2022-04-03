package com.xwtec.xwserver.util.mail;
 public class MailMsg {

 private  String[] toAddress;

 private  String[] ccAddress;

 private  String subject;

 private  String content;

 private  String sourceName;


public String[] getToAddress(){
    return toAddress;
}


public void setToAddress(String toAddress){
    this.toAddress = new String[1];
    this.toAddress[0] = toAddress;
}


public String getSubject(){
    return subject;
}


public void setSubject(String subject){
    this.subject = subject;
}


public void setContent(String content){
    this.content = content;
}


public String getSourceName(){
    return sourceName;
}


public String[] getCcAddress(){
    return ccAddress;
}


public String getContent(){
    return content;
}


public void setSourceName(String sourceName){
    this.sourceName = sourceName;
}


public void setCcAddress(String[] ccAddress){
    this.ccAddress = ccAddress;
}


}