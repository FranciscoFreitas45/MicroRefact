package com.ushahidi.swiftriver.core.api.dto.ModifyAccountDTO;
 import org.codehaus.jackson.annotate.JsonProperty;
public class User {

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


public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public String getCurrentPassword(){
    return currentPassword;
}


public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return email;
}


}