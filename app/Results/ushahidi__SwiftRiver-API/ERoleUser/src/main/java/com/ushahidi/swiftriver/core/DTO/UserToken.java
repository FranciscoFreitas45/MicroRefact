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


}