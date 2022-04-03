package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import java.util.Date;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
import com.yalcin.Request.ProductRequest;
import com.yalcin.Request.Impl.ProductRequestImpl;
import com.yalcin.DTO.Product;
@Entity
@Table(name = "store", schema = "public")
public class Store {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

 private  Date timestap;

 private  boolean enabled;

@Transient
 private  User user;

@Transient
 private  Product product;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Integer id;

@Transient
 private ProductRequest productrequest = new ProductRequestImpl();;

public Store() {
}
public void setTimestap(Date timestap){
    this.timestap = timestap;
}


public void setProduct(Product product){
 productrequest.setProduct(product,this.id);
}



public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public Product getProduct(){
  this.product = productrequest.getProduct(this.id);
return this.product;
}


public Date getTimestap(){
    return timestap;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public boolean isEnabled(){
    return enabled;
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