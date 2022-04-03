package org.live.websocket.chat;
 public class Message {

 private  String fromChatRoomNum;

 private  String destination;

 private  String account;

 private  String nickname;

 private  String content;

 private  int messageType;


public int getMessageType(){
    return messageType;
}


public void setContent(String content){
    this.content = content;
}


public String getDestination(){
    return destination;
}


public String getNickname(){
    return nickname;
}


public String getFromChatRoomNum(){
    return fromChatRoomNum;
}


public String getAccount(){
    return account;
}


public String getContent(){
    return content;
}


public void setAccount(String account){
    this.account = account;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setFromChatRoomNum(String fromChatRoomNum){
    this.fromChatRoomNum = fromChatRoomNum;
}


public void setDestination(String destination){
    this.destination = destination;
}


public void setMessageType(int messageType){
    this.messageType = messageType;
}


}