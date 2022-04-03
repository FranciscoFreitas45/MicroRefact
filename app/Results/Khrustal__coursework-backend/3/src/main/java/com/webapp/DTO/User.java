package com.webapp.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class User {

 private  Long id;

 private  String username;

 private  String password;

 private  Set<Role> roles;

public User() {
}public User(String username, String password) {
    this.username = username;
    this.password = password;
}
public String getPassword(){
    return password;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Set<Role> getRoles(){
    return roles;
}


}