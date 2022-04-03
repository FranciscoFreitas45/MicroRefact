package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.util.GravatarUtil;
public class User {

 private  long id;

 private  String email;

 private  String name;

 private  String username;

 private  String password;

 private  int logins;

 private  Boolean active;

 private  Boolean expired;

 private  Boolean locked;

 private  Date lastLoginDate;

 private  Date dateCreated;

 private  Set<Role> roles;

 private  Set<UserToken> tokens;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";

public User() {
}
public String getName(){
    return name;
}


public Date getDateCreated(){
    return dateCreated;
}


public String getAvatar(){
    return GravatarUtil.gravatar(email);
}


public Set<UserToken> getTokens(){
    return tokens;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public int getLogins(){
    return logins;
}


public Set<Role> getRoles(){
    return roles;
}


public Date getLastLoginDate(){
    return lastLoginDate;
}


public Boolean getExpired(){
    return expired;
}


public Boolean getLocked(){
    return locked;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public Boolean getActive(){
    return active;
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActive(Boolean active){
    this.active = active;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setActive"))

.queryParam("active",active)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
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


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExpired(Boolean expired){
    this.expired = expired;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExpired"))

.queryParam("expired",expired)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLocked(Boolean locked){
    this.locked = locked;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLocked"))

.queryParam("locked",locked)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoles"))

.queryParam("roles",roles)
;
restTemplate.put(builder.toUriString(),null);
}


}