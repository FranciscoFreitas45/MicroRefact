package com.easyshopping;
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
public void setUsername(String username){
    this.username = username;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return username;
}


public String getUsername(){
    return username;
}


}