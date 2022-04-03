package com.gbcom.common.template.xml.sys;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("TargetVer")
public class TargetVer {

@XStreamAsAttribute
 private  String deType;

@XStreamAsAttribute
 private  String verType;

@XStreamAsAttribute
 private  String tarVer;

@XStreamAsAttribute
 private  String desc;


public String getTarVer(){
    return tarVer;
}


public String getDeType(){
    return deType;
}


public String getVerType(){
    return verType;
}


public void setDeType(String deType){
    this.deType = deType;
}


public String getDesc(){
    return desc;
}


public void setTarVer(String tarVer){
    this.tarVer = tarVer;
}


public void setDesc(String desc){
    this.desc = desc;
}


public void setVerType(String verType){
    this.verType = verType;
}


}