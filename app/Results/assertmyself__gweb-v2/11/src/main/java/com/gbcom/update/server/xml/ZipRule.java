package com.gbcom.update.server.xml;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("ZipRule")
public class ZipRule {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String include;

@XStreamAsAttribute
 private  String exclude;


public void setName(String name){
    this.name = name;
}


public void setInclude(String include){
    this.include = include;
}


public String getName(){
    return name;
}


public String getInclude(){
    return include;
}


public void setExclude(String exclude){
    this.exclude = exclude;
}


public String getExclude(){
    return exclude;
}


}