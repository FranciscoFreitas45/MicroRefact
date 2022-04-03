package com.gbcom.common.template.xml.prefilter;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("condition")
public class PreFilterCondition {

@XStreamAsAttribute
 private  String sysmodel;

@XStreamAsAttribute
 private  String version;

@XStreamAsAttribute
 private  String oper;

@XStreamAsAttribute
 private  String value;


public String getVersion(){
    return version;
}


public String getValue(){
    return value;
}


public void setSysmodel(String sysmodel){
    this.sysmodel = sysmodel;
}


public void setVersion(String version){
    this.version = version;
}


public String getSysmodel(){
    return sysmodel;
}


public String getOper(){
    return oper;
}


public void setValue(String value){
    this.value = value;
}


@Override
public String toString(){
    return "DevFilterCondition [oper=" + oper + ", sysmodel=" + sysmodel + ", value=" + value + ", version=" + version + "]";
}


public void setOper(String oper){
    this.oper = oper;
}


}