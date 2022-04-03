package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.Date;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
@Entity
@Table(name = "seller_begin", schema = "public")
public class SellerBegin {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Integer id;

@Size(min = 50, max = 1000)
@Column(name = "content")
 private  String content;

 private  Date timestap;

@Transient
 private  User user;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public SellerBegin() {
}public SellerBegin(String content) {
    this.content = content;
}
public void setTimestap(Date timestap){
    this.timestap = timestap;
}


public void setContent(String content){
    this.content = content;
}


public Date getTimestap(){
    return timestap;
}


public String getContent(){
    return content;
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


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



}