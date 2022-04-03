package com.csquard.mregister.DTO;
 import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
public class User extends DateAudit{

 private  Long id;

 private  String name;

 private  String username;

 private  String email;

 private  String password;

 private  Set<Roles> roles;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

public User() {
    super();
}public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
}
public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Set<Roles> getRoles(){
    return roles;
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoles(Set<Roles> roles){
    this.roles = roles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoles"))

.queryParam("roles",roles)
;
restTemplate.put(builder.toUriString(),null);
}


}