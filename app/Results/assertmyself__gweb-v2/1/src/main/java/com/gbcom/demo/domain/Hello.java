package com.gbcom.demo.domain;
 import java.io.Serializable;
import java.util.Date;
public class Hello implements Serializable{

 private  long serialVersionUID;

 private  java.lang.Long id;

 private  java.lang.String name;

 private  java.lang.Integer age;

 private  java.lang.String address;

 private  java.lang.String description;

 private  java.sql.Timestamp createTime;


public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.Integer getAge(){
    return age;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getName(){
    return name;
}


public void setAddress(java.lang.String address){
    this.address = address;
}


public void setId(java.lang.Long id){
    this.id = id;
}


public java.lang.Long getId(){
    return id;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public java.lang.String getAddress(){
    return address;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public java.lang.String getDescription(){
    return description;
}


public void setAge(java.lang.Integer age){
    this.age = age;
}


}