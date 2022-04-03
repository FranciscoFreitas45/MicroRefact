package com.byr.warehouse.DTO;
 import javax.persistence;
public class User {

 private  Long id;

 private  String username;

 private  String password;

 private  Integer role;

 private  Integer status;


public String getPassword(){
    return password;
}


public Integer getRole(){
    return role;
}


public Long getId(){
    return id;
}


public Integer getStatus(){
    return status;
}


public String getUsername(){
    return username;
}


}