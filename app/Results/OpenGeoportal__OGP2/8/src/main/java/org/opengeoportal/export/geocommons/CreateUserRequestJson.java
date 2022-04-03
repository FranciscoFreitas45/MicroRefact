package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class CreateUserRequestJson {

@JsonProperty("user")
 private User user;

 private String full_name;

 private String login;

 private String password;

 private String password_confirmation;

 private String email;

CreateUserRequestJson() {
    this.user = new User();
}
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


public User getUser(){
    return user;
}


public String getEmail(){
    return email;
}


public void setUser(User user){
    this.user = user;
}


public void setPassword_confirmation(String password_confirmation){
    this.password_confirmation = password_confirmation;
}


}