package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("node")
public class MibNode {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String oid;

@XStreamAsAttribute
 private  boolean required;

@XStreamAsAttribute
 private  String regex;

@XStreamAsAttribute
 private  String defValue;

@XStreamAsAttribute
 private  String type;


public void setName(String name){
    this.name = name;
}


public boolean isRequired(){
    return required;
}


public void setRequired(boolean required){
    this.required = required;
}


public String getRegex(){
    return regex;
}


public String getName(){
    return name;
}


public void setRegex(String regex){
    this.regex = regex;
}


public String getType(){
    return type;
}


public void setOid(String oid){
    this.oid = oid;
}


public void setDefValue(String defValue){
    this.defValue = defValue;
}


public String getOid(){
    return oid;
}


public void setType(String type){
    this.type = type;
}


public String getDefValue(){
    return defValue;
}


}