package com.ukefu.webim.web.model;
 import java.io.Serializable;
public class UKFacet implements Serializable{

 private  long serialVersionUID;

 private  String key;

 private  int value;

 private  User user;

public UKFacet() {
}public UKFacet(String key, int value) {
    this.key = key;
    this.value = value;
}
public String getKey(){
    return key;
}


public int getValue(){
    return value;
}


public void setValue(int value){
    this.value = value;
}


public User getUser(){
    return user;
}


public void setUser(User user){
    this.user = user;
}


public void setKey(String key){
    this.key = key;
}


}