package com.gbcom.common.template.xml.tpl;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("Tplclz")
public class Tplclz {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String tab1;

@XStreamAsAttribute
 private  String pline;

@XStreamAsAttribute
 private  boolean inner;

@XStreamAsAttribute
 private  String alias;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setInner(boolean inner){
    this.inner = inner;
}


public String getPline(){
    return pline;
}


public String getTab1(){
    return tab1;
}


public void setPline(String pline){
    this.pline = pline;
}


public void setAlias(String alias){
    this.alias = alias;
}


public String getAlias(){
    return alias;
}


public void setTab1(String tab1){
    this.tab1 = tab1;
}


public boolean isInner(){
    return inner;
}


}