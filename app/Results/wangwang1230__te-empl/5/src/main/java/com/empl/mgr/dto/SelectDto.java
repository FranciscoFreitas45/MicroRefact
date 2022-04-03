package com.empl.mgr.dto;
 import java.io.Serializable;
public class SelectDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

public SelectDto() {
// TODO Auto-generated constructor stub
}public SelectDto(long id, String name) {
    super();
    this.id = id;
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


@Override
public String toString(){
    return "SelectDto [id:" + id + ", name:" + name + "]";
}


}