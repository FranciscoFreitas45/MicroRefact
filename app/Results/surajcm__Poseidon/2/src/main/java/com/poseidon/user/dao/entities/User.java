package com.poseidon.user.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user")
public class User extends CommonEntity{

@Column(name = "name")
 private  String name;

@Column(name = "email")
 private  String email;

@Column(name = "password")
 private  String password;

@Column(name = "role")
 private  String role;

@Column(name = "expired")
 private  Boolean expired;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public void setEmail(String email){
    this.email = email;
}


public void setExpired(Boolean expired){
    this.expired = expired;
}


public void setRole(String role){
    this.role = role;
}


public String getRole(){
    return role;
}


public String getEmail(){
    return email;
}


public Boolean getExpired(){
    return expired;
}


}