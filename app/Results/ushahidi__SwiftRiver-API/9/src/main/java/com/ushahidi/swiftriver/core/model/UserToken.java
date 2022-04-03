package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "user_tokens")
public class UserToken {

@Id
@GeneratedValue
 private  long id;

 private  String token;

@Temporal(TemporalType.TIMESTAMP)
 private  Date created;

@Temporal(TemporalType.TIMESTAMP)
 private  Date expires;

@ManyToOne
@JoinColumn(name = "user_id")
 private  User user;


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


public void setExpires(Date expires){
    this.expires = expires;
}


public User getUser(){
    return user;
}


public void setId(long id){
    this.id = id;
}


public Date getCreated(){
    return created;
}


public long getId(){
    return id;
}


public void setCreated(Date created){
    this.created = created;
}


public void setUser(User user){
    this.user = user;
}


public Date getExpires(){
    return expires;
}


}