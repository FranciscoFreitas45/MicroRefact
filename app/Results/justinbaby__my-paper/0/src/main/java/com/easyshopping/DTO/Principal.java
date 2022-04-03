package com.easyshopping.DTO;
 import java.io.Serializable;
public class Principal implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String username;

/**
 * @param id
 *            ID
 * @param username
 *            用户名
 */
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