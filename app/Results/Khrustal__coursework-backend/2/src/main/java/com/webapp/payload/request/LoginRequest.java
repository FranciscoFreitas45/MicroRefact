package com.webapp.payload.request;
 import javax.validation.constraints.NotBlank;
public class LoginRequest {

@NotBlank
 private  String username;

@NotBlank
 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public String getUsername(){
    return username;
}


}