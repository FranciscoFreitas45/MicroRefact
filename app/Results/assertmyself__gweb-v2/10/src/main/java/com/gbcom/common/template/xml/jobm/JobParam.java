package com.gbcom.common.template.xml.jobm;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("jobParam")
public class JobParam {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String value;


public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public void setValue(String value){
    this.value = value;
}


}