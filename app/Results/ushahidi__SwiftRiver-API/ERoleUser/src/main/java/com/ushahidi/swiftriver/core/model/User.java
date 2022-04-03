package com.ushahidi.swiftriver.core.model;
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
import com.ushahidi.swiftriver.core.Request.UserTokenRequest;
import com.ushahidi.swiftriver.core.Request.Impl.UserTokenRequestImpl;
import com.ushahidi.swiftriver.core.DTO.UserToken;
@Entity
@Table(name = "users")
public class User {

@Id
@GeneratedValue
 private  long id;

 private  String email;

 private  String name;

 private  String username;

 private  String password;

 private  int logins;

 private  Boolean active;

 private  Boolean expired;

 private  Boolean locked;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_login")
 private  Date lastLoginDate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "created_date")
 private  Date dateCreated;

@ManyToMany
@JoinTable(name = "roles_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

@Transient
 private  Set<UserToken> tokens;

@Transient
 private UserTokenRequest usertokenrequest = new UserTokenRequestImpl();;

public User() {
}
public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
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
  this.tokens = usertokenrequest.getTokens(this.id);
return this.tokens;
}}



public void setLogins(int logins){
    this.logins = logins;
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


public void setTokens(Set<UserToken> tokens){
usertokenrequest.setTokens(tokens,this.id);
 this.tokens = tokens;
}



public void setExpired(Boolean expired){
    this.expired = expired;
}


public void setId(long id){
    this.id = id;
}


public void setDateCreated(Date dateCreated){
    this.dateCreated = dateCreated;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Set<Role> getRoles(){
    return roles;
}


public Date getLastLoginDate(){
    return lastLoginDate;
}


public void setUsername(String username){
    this.username = username;
}


public Boolean getExpired(){
    return expired;
}


public Boolean getLocked(){
    return locked;
}


public void setLocked(Boolean locked){
    this.locked = locked;
}


public String getPassword(){
    return password;
}


public void setActive(Boolean active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return email;
}


public Boolean getActive(){
    return active;
}


public void setLastLoginDate(Date lastLoginDate){
    this.lastLoginDate = lastLoginDate;
}


}