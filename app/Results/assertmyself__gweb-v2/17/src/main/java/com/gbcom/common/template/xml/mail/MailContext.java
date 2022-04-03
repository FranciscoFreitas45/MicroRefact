package com.gbcom.common.template.xml.mail;
 import java.util.Set;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("MailContext")
public class MailContext {

@XStreamAsAttribute
 private  String senderAddr;

@XStreamAsAttribute
 private  String senderPwd;

@XStreamAsAttribute
 private  String senderName;

@XStreamAsAttribute
 private  String host;

@XStreamAsAttribute
 private  String port;

@XStreamAsAttribute
 private  String protocol;

@XStreamAsAttribute
 private  String auth;

@XStreamAlias("receivers")
 private  Set<MailReceiver> receivers;


public void setHost(String host){
    this.host = host;
}


public void setAuth(String auth){
    this.auth = auth;
}


public String getAuth(){
    return auth;
}


public String getSenderName(){
    return senderName;
}


public void setProtocol(String protocol){
    this.protocol = protocol;
}


public String getSenderPwd(){
    return senderPwd;
}


public void setSenderPwd(String senderPwd){
    this.senderPwd = senderPwd;
}


public String getProtocol(){
    return protocol;
}


public void setPort(String port){
    this.port = port;
}


public Set<MailReceiver> getReceivers(){
    return receivers;
}


public void setReceivers(Set<MailReceiver> receivers){
    this.receivers = receivers;
}


public String getSenderAddr(){
    return senderAddr;
}


public String getPort(){
    return port;
}


public void setSenderName(String senderName){
    this.senderName = senderName;
}


public void setSenderAddr(String senderAddr){
    this.senderAddr = senderAddr;
}


public String getHost(){
    return host;
}


}