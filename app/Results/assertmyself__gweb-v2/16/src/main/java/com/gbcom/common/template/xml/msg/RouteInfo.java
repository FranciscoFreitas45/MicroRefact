package com.gbcom.common.template.xml.msg;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("route")
public class RouteInfo {

@XStreamAsAttribute
 private  int msgid;

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  int msgtype;

@XStreamAsAttribute
 private  String processor;

@XStreamAsAttribute
 private  String modulename;


public void setName(String name){
    this.name = name;
}


public String getProcessor(){
    return processor;
}


public void setProcessor(String processor){
    this.processor = processor;
}


public String getModulename(){
    return modulename;
}


public String getName(){
    return name;
}


public int getMsgid(){
    return msgid;
}


public int getMsgtype(){
    return msgtype;
}


public void setModulename(String modulename){
    this.modulename = modulename;
}


public void setMsgid(int msgid){
    this.msgid = msgid;
}


public void setMsgtype(int msgtype){
    this.msgtype = msgtype;
}


}