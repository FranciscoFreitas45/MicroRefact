package org.jeecgframework.jwt.model;
 public class TokenModel {

 private  String username;

 private  String token;

public TokenModel(String username, String token) {
    this.username = username;
    this.token = token;
}
public void setUsername(String username){
    this.username = username;
}


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


public String getUsername(){
    return username;
}


}