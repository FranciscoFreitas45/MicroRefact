package com.yalcin.DTO;
 import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import com.yalcin.Request.AdressRequest;
import com.yalcin.Request.Impl.AdressRequestImpl;
import com.yalcin.DTO.Adress;
public class User {

 private  Integer id;

 private  String username;

 private  boolean enabled;

 private  String email;

 private  String password;

 private  String name;

 private  String lastname;

 private  String age;

 private  String phoneNumber;

 private  Set<Role> roles;

 private  Set<ActiveSessions> activeSessions;

 private  Set<Adress> adress;

public User() {
}public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
}
public String getAge(){
    return age;
}


public String getName(){
    return name;
}


public Integer getId(){
    return id;
}


public String getUsername(){
    return username;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public Set<Role> getRoles(){
    return roles;
}


public String getPassword(){
    return password;
}


public Set<ActiveSessions> getActiveSessions(){
    return this.activeSessions;
}


public String getEmail(){
    return email;
}


public String getLastname(){
    return lastname;
}


public Set<Adress> getAdress(){
    return adress;
}


}