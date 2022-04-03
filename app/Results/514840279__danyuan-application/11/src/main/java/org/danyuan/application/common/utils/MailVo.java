package org.danyuan.application.common.utils;
 public class MailVo {

 private  String fromMail;

 private  String mail;

 private  String code;

 private  String message;

 private  String title;


public String getFromMail(){
    return fromMail;
}


public String getTitle(){
    return title;
}


public void setMail(String mail){
    this.mail = mail;
}


public void setCode(String code){
    this.code = code;
}


public String getMessage(){
    return message;
}


public void setTitle(String title){
    this.title = title;
}


@Override
public String toString(){
    return "MailVo [mail=" + mail + ", code=" + code + ", message=" + message + "]";
}


public void setMessage(String message){
    this.message = message;
}


public void setFromMail(String fromMail){
    this.fromMail = fromMail;
}


public String getCode(){
    return code;
}


public String getMail(){
    return mail;
}


}