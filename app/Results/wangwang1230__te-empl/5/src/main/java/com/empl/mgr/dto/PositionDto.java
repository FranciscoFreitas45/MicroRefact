package com.empl.mgr.dto;
 import java.io.Serializable;
public class PositionDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

public PositionDto() {
// TODO Auto-generated constructor stub
}public PositionDto(long poId, String poName) {
    super();
    this.id = poId;
    this.name = poName;
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
    return "PositionDto [id:" + id + ", name:" + name + "]";
}


}