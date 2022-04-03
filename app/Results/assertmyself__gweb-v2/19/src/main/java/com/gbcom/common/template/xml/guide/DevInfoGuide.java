package com.gbcom.common.template.xml.guide;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("DevInfoGuide")
public class DevInfoGuide {

@XStreamAsAttribute
 private  String code;

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String desc;

@XStreamAsAttribute
 private  String mibNode;

@XStreamAsAttribute
 private  String entityClass;

@XStreamAsAttribute
 private  boolean multi;

@XStreamAsAttribute
 private  boolean autoJob;


public void setName(String name){
    this.name = name;
}


public void setEntityClass(String entityClass){
    this.entityClass = entityClass;
}


public String getName(){
    return name;
}


public void setCode(String code){
    this.code = code;
}


public boolean isAutoJob(){
    return autoJob;
}


public boolean isMulti(){
    return multi;
}


public String getEntityClass(){
    return entityClass;
}


public void setAutoJob(boolean autoJob){
    this.autoJob = autoJob;
}


public void setMulti(boolean multi){
    this.multi = multi;
}


public String getDesc(){
    return desc;
}


public String getMibNode(){
    return mibNode;
}


public void setDesc(String desc){
    this.desc = desc;
}


public void setMibNode(String mibNode){
    this.mibNode = mibNode;
}


public String getCode(){
    return code;
}


}