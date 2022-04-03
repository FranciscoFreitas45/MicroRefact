package com.gbcom.common.template.xml.sys;
 import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
public class L2tpConfig {

@XStreamAsAttribute
 private  String l2tpServerIp;

@XStreamAsAttribute
 private  String l2tpUsrName;

@XStreamAsAttribute
 private  String l2tpUsrPass;


public void setL2tpUsrPass(String usrPass){
    l2tpUsrPass = usrPass;
}


public void setL2tpServerIp(String serverIp){
    l2tpServerIp = serverIp;
}


public void setL2tpUsrName(String usrName){
    l2tpUsrName = usrName;
}


public String getL2tpServerIp(){
    return l2tpServerIp;
}


public String getL2tpUsrPass(){
    return l2tpUsrPass;
}


public String getL2tpUsrName(){
    return l2tpUsrName;
}


}