package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class UserToken {

 private  long id;

 private  String token;

 private  Date created;

 private  Date expires;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getToken(){
    return token;
}


public User getUser(){
    return user;
}


public Date getCreated(){
    return created;
}


public long getId(){
    return id;
}


public Date getExpires(){
    return expires;
}


public void setToken(String token){
    this.token = token;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setToken"))

.queryParam("token",token)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExpires(Date expires){
    this.expires = expires;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExpires"))

.queryParam("expires",expires)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUser(User user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreated(Date created){
    this.created = created;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreated"))

.queryParam("created",created)
;
restTemplate.put(builder.toUriString(),null);
}


}