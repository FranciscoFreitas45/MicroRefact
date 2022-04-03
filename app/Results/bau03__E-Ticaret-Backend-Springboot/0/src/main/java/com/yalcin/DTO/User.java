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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public User() {
}public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
}
public void setPassword(String password){
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


public void setAdress(Set<Adress> adress){
    this.adress = adress;
}


public String getPassword(){
    return password;
}


public Set<ActiveSessions> getActiveSessions(){
    return this.activeSessions;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
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


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLastname(String lastname){
    this.lastname = lastname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLastname"))

.queryParam("lastname",lastname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAge(String age){
    this.age = age;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAge"))

.queryParam("age",age)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEnabled"))

.queryParam("enabled",enabled)
;
restTemplate.put(builder.toUriString(),null);
}


}