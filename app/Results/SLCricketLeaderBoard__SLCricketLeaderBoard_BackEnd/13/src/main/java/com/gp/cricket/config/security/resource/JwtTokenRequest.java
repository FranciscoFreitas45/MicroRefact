package com.gp.cricket.config.security.resource;
 import java.io.Serializable;
public class JwtTokenRequest implements Serializable{

 private  long serialVersionUID;

 private  String username;

 private  String password;

public JwtTokenRequest() {
    super();
}public JwtTokenRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
}
public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return this.password;
}


public void setUsername(String username){
    this.username = username;
}


public String getUsername(){
    return this.username;
}


}