package com.empl.mgr.dto;
 import java.io.Serializable;
public class AddressDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

public AddressDto(long id, String name) {
    super();
    this.id = id;
    this.name = name;
}public AddressDto() {
// TODO Auto-generated constructor stub
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
    return "AddressDto [id:" + id + ", name:" + name + "]";
}


}