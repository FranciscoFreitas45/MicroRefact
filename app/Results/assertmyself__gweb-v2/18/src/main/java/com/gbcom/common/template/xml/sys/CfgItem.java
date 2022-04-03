package com.gbcom.common.template.xml.sys;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("ConfigItem")
public class CfgItem {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String desc;

@XStreamAsAttribute
 private  String clxName;

@XStreamAsAttribute
 private  String priority;

@XStreamAsAttribute
 private  boolean multi;

@XStreamAsAttribute
 private  boolean autoSync;


public void setName(String name){
    this.name = name;
}


public boolean isMulti(){
    return multi;
}


public String getName(){
    return name;
}


public String getClxName(){
    return clxName;
}


public void setMulti(boolean multi){
    this.multi = multi;
}


public String getPriority(){
    return priority;
}


public String getDesc(){
    return desc;
}


public void setDesc(String desc){
    this.desc = desc;
}


public void setClxName(String clxName){
    this.clxName = clxName;
}


public boolean isAutoSync(){
    return autoSync;
}


public void setPriority(String priority){
    this.priority = priority;
}


public void setAutoSync(boolean autoSync){
    this.autoSync = autoSync;
}


}