package org.opengeoportal.export.geocommons.CreateUserRequestJson;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class User {

 private String full_name;

 private String login;

 private String password;

 private String password_confirmation;

 private String email;


public String getFull_name(){
    return full_name;
}


public void setFull_name(String full_name){
    this.full_name = full_name;
}


public String getLogin(){
    return login;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getPassword_confirmation(){
    return password_confirmation;
}


public void setEmail(String email){
    this.email = email;
}


public void setLogin(String login){
    this.login = login;
}


public String getEmail(){
    return email;
}


public void setPassword_confirmation(String password_confirmation){
    this.password_confirmation = password_confirmation;
}


}