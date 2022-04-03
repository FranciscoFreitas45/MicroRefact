package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateAccountDTO {

 private  String name;

@JsonProperty("account_path")
 private  String accountPath;

 private  String email;

 private  String password;

@JsonProperty("private")
 private  boolean accountPrivate;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setAccountPrivate(boolean accountPrivate){
    this.accountPrivate = accountPrivate;
}


public String getName(){
    return name;
}


public void setEmail(String email){
    this.email = email;
}


public boolean isAccountPrivate(){
    return accountPrivate;
}


public String getAccountPath(){
    return accountPath;
}


public String getEmail(){
    return email;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


}