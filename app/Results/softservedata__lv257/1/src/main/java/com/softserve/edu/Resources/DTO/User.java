package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import org.jboss.aerogear.security.otp.api.Base32;
import javax.persistence;
import java.util.Collection;
public class User {

 private  Long id;

 private  String username;

 private  String password;

 private  boolean enabled;

 private  String secret;

 private  Collection<Role> roles;

 private  UserDetails userDetails;

 private  VerificationToken verificationToken;

 private  Collection<ResourceRequest> requestsByAdmin;

 private  Collection<ResourceRequest> requestsByRegister;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

public User() {
    this.secret = Base32.random();
    this.enabled = false;
}
public VerificationToken getVerificationToken(){
    return verificationToken;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public UserDetails getUserDetails(){
    return userDetails;
}


public String getPassword(){
    return password;
}


public String getSecret(){
    return secret;
}


public Collection<Role> getRoles(){
    return roles;
}


public User setRoles(Collection<Role> roles){
    this.roles = roles;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoles"))

.queryParam("roles",roles)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


public User setPassword(String password){
    this.password = password;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPassword"))

.queryParam("password",password)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


public User setUsername(String username){
    this.username = username;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUsername"))

.queryParam("username",username)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


public User setUserDetails(UserDetails userDetails){
  this.userDetails = userdetailsrequest.setUserDetails(userDetails,this.idO4D4);
return this.userDetails;
  


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUserDetails"))

.queryParam("userDetails",userDetails)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


public boolean isEnabled(){
    return enabled;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isEnabled"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public User setEnabled(boolean enabled){
    this.enabled = enabled;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEnabled"))

.queryParam("enabled",enabled)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


}