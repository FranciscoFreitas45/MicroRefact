package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserParameters", propOrder = { "sendCtg", "sndr", "rcpn" })
public class UserParameters {

@XmlElement(name = "SendCtg")
 protected  Rtm02Parameter sendCtg;

@XmlElement(name = "Sndr")
 protected  String sndr;

@XmlElement(name = "Rcpn")
 protected  String rcpn;


public String getRcpn(){
    return rcpn;
}


public Rtm02Parameter getSendCtg(){
    return sendCtg;
}


public String getSndr(){
    return sndr;
}


public void setSndr(String value){
    this.sndr = value;
}


public void setSendCtg(Rtm02Parameter value){
    this.sendCtg = value;
}


public void setRcpn(String value){
    this.rcpn = value;
}


}