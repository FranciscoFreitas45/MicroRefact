package com.zammc.response;
 public class Message {

 private  MessageStatus status;

 private  MessageTitle title;

 private  String message;

public Message(MessageStatus status, MessageTitle title, String message) {
    this.status = status;
    this.title = title;
    this.message = message;
}
public MessageTitle getTitle(){
    return title;
}


public void setTitle(MessageTitle title){
    this.title = title;
}


public String getMessage(){
    return message;
}


public MessageStatus getStatus(){
    return status;
}


public void setMessage(String message){
    this.message = message;
}


public void setStatus(MessageStatus status){
    this.status = status;
}


}