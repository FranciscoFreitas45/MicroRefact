package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
@Entity
@Table(name = "purse", schema = "public")
public class Purse {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Size(min = 1, max = 50)
@Column(name = "balance")
 private  float balance;

@Transient
 private  User user;

 private  Date timestap;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Purse(@Size(min = 1, max = 50) float balance) {
    this.balance = balance;
}public Purse() {
}
public void setTimestap(Date timestap){
    this.timestap = timestap;
}


public float getBalance(){
    return balance;
}


public Date getTimestap(){
    return timestap;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setBalance(float balance){
    this.balance = balance;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



}