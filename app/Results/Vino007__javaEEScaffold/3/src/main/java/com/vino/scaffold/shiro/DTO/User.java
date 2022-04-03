package com.vino.scaffold.shiro.DTO;
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.vino.scaffold.entity.base.BaseEntity;
public class User extends BaseEntity<Long>{

 private  String username;

 private  String password;

 private  String userAlias;

 private  Date lastLoginTime;

 private  Date loginTime;

 private  String salt;

 private  Boolean locked;

 private  Set<Role> roles;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public User() {
}public User(String username, String password, String alias) {
    this.username = username;
    this.password = password;
    this.userAlias = alias;
}
public String getSalt(){
    return salt;
}


public Boolean getLocked(){
    return locked;
}


public String getUsername(){
    return username;
}


public String getUserAlias(){
    return userAlias;
}


public String getPassword(){
    return password;
}


public Date getLoginTime(){
    return loginTime;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public Set<Role> getRoles(){
    return roles;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLastLoginTime"))

.queryParam("lastLoginTime",lastLoginTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLoginTime(Date loginTime){
    this.loginTime = loginTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLoginTime"))

.queryParam("loginTime",loginTime)
;
restTemplate.put(builder.toUriString(),null);
}


}