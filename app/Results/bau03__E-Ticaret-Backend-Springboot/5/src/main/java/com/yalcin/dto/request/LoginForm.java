package com.yalcin.dto.request;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class LoginForm {

@NotBlank
@Size(min = 3, max = 50)
 private  String username;

@NotBlank
@Size(min = 6, max = 20)
 private  String password;

 private  boolean rememberMe;

 private  String captcha;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public void setRememberMe(boolean rememberMe){
    this.rememberMe = rememberMe;
}


public void setCaptcha(String captcha){
    this.captcha = captcha;
}


public boolean isRememberMe(){
    return rememberMe;
}


public String getCaptcha(){
    return captcha;
}


public String getUsername(){
    return username;
}


}