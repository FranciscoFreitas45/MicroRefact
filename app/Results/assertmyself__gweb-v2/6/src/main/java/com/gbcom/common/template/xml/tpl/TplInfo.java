package com.gbcom.common.template.xml.tpl;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("TplInfo")
public class TplInfo {

@XStreamAsAttribute
 private  String id;

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String sysmodels;

@XStreamAsAttribute
 private  String jsp;

@XStreamAlias("list")
 private  List<Tplclz> clzList;


public void setName(String name){
    this.name = name;
}


public String getSysmodels(){
    return sysmodels;
}


public void setSysmodels(String sysmodels){
    this.sysmodels = sysmodels;
}


public String getName(){
    return name;
}


public void setClzList(List<Tplclz> clzList){
    this.clzList = clzList;
}


public List<Tplclz> getClzList(){
    return clzList;
}


public void setJsp(String jsp){
    this.jsp = jsp;
}


public void setId(String id){
    this.id = id;
}


public String getJsp(){
    return jsp;
}


public String getId(){
    return id;
}


}