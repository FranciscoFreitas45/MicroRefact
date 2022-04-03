package com.ushahidi.swiftriver.core.api.dto;
 public class ActivateAccountDTO {

 private  String email;

 private  String token;


public void setEmail(String email){
    this.email = email;
}


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


public String getEmail(){
    return email;
}


}