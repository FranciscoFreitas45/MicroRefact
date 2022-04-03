package com.zis.oldapi.response;
 public class TokenResponse extends BaseApiResponse{

 private  String token;


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


}