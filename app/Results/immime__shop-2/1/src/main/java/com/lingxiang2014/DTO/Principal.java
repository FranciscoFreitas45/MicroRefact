package com.lingxiang2014.DTO;
 import java.io.Serializable;
public class Principal implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String username;

public Principal(Long id, String username) {
    this.id = id;
    this.username = username;
}
public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


}