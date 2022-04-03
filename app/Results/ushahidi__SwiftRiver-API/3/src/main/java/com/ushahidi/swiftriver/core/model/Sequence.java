package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "seq")
public class Sequence {

@Id
 private  String name;

 private  long id;


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


}