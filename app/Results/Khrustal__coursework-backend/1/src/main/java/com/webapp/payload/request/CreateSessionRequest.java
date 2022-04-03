package com.webapp.payload.request;
 import javax.validation.constraints.NotBlank;
public class CreateSessionRequest {

@NotBlank
 private  Long userId;

@NotBlank
 private  String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}