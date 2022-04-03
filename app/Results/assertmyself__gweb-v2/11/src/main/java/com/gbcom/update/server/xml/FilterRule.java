package com.gbcom.update.server.xml;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("FilterRule")
public class FilterRule {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String filterConst;

@XStreamAsAttribute
 private  String filterContent;


public void setName(String name){
    this.name = name;
}


public void setFilterContent(String filterContent){
    this.filterContent = filterContent;
}


public String getFilterContent(){
    return filterContent;
}


public String getName(){
    return name;
}


public void setFilterConst(String filterConst){
    this.filterConst = filterConst;
}


public String getFilterConst(){
    return filterConst;
}


}