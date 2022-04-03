package com.gbcom.common.template.xml.mail;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("MailReceiver")
public class MailReceiver {

@XStreamAsAttribute
 private  String receiverAddr;

@XStreamAsAttribute
 private  String receiverType;


public void setReceiverType(String receiverType){
    this.receiverType = receiverType;
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public String getReceiverType(){
    return receiverType;
}


}