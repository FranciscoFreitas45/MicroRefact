package com.designpattern.prototype;
 import java.io.Serializable;
public class PlainObject implements Serializable,Cloneable{

 private  long serialVersionUID;

 private  String name;

 private  String id;

public PlainObject(String name, String id) {
    this.name = name;
    this.id = id;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Object clone(){
    PlainObject po = (PlainObject) super.clone();
    return po;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}