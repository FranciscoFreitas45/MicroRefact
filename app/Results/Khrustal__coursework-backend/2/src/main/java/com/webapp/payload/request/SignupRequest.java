package com.webapp.payload.request;
 import java.util.Set;
import javax.validation.constraints;
public class SignupRequest {

@NotBlank
@Size(min = 3, max = 20)
 private  String username;

 private  Set<String> role;

@NotBlank
@Size(min = 6, max = 40)
 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public void setRole(Set<String> role){
    this.role = role;
}


public Set<String> getRole(){
    return this.role;
}


public String getUsername(){
    return username;
}


}