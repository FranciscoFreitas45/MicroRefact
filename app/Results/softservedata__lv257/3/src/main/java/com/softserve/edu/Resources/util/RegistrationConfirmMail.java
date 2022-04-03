package com.softserve.edu.Resources.util;
 public class RegistrationConfirmMail extends Mail{

 private String host;

 private String token;

 private String from;

 private String userId;

public RegistrationConfirmMail(String receiver) {
    super(receiver, "Registration Confirmation", "registrationConfirmation.vm");
}
public void setHost(String host){
    this.host = host;
}


public void setToken(String token){
    this.token = token;
}


public void setFrom(String from){
    this.from = from;
}


public String getToken(){
    return token;
}


public String getUserId(){
    return userId;
}


public String getHost(){
    return host;
}


public void setUserId(String userId){
    this.userId = userId;
}


public String getFrom(){
    return from;
}


}