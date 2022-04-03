package com.yalcin.dto.request;
 import java.util.Set;
import javax.validation.constraints;
public class SignUpForm {

@NotBlank
@Size(min = 3, max = 50)
 private  String username;

@NotBlank
@Size(max = 60)
@Email
 private  String email;

 private  Set<String> role;

@NotBlank
@Size(min = 6, max = 20)
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


public void setEmail(String email){
    this.email = email;
}


public void setRole(Set<String> role){
    this.role = role;
}


public Set<String> getRole(){
    return this.role;
}


public String getEmail(){
    return email;
}


public String getUsername(){
    return username;
}


}