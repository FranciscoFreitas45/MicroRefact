package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class ModifyAccountDTO {

@JsonProperty("account_path")
 private  String accountPath;

 private  User owner;

@JsonProperty("private")
 private  Boolean accountPrivate;

@JsonProperty("river_quota_remaining")
 private  Integer riverQuotaRemaining;

 private  String token;

 private  String name;

 private  String email;

 private  String password;

@JsonProperty("current_password")
 private  String currentPassword;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public void setCurrentPassword(String currentPassword){
    this.currentPassword = currentPassword;
}


public void setAccountPrivate(Boolean accountPrivate){
    this.accountPrivate = accountPrivate;
}


public String getName(){
    return name;
}


public String getCurrentPassword(){
    return currentPassword;
}


public void setRiverQuotaRemaining(Integer riverQuotaRemaining){
    this.riverQuotaRemaining = riverQuotaRemaining;
}


public String getAccountPath(){
    return accountPath;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public User getOwner(){
    return owner;
}


public void setOwner(User owner){
    this.owner = owner;
}


public Boolean getAccountPrivate(){
    return accountPrivate;
}


public String getPassword(){
    return password;
}


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


public Integer getRiverQuotaRemaining(){
    return riverQuotaRemaining;
}


}