package com.gbcom.common.template.xml.msg;
 import java.io.Serializable;
import java.util.Date;
public class MessageInfo implements Serializable{

 private  long serialVersionUID;

 private  int messageID;

 private  Date time;

 private  Serializable msgBody;


public Serializable getMsgBody(){
    return msgBody;
}


public int getMessageID(){
    return messageID;
}


public Date getTime(){
    return time;
}


@Override
public String toString(){
    return "MessageInfo [messageID=" + messageID + ", msgBody=" + msgBody + "]";
}


public void setMessageID(int messageID){
    this.messageID = messageID;
}


public void setMsgBody(Serializable msgBody){
    this.msgBody = msgBody;
}


public void setTime(Date time){
    this.time = time;
}


}