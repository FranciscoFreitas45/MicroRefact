package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.Date;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
import com.yalcin.Request.ProductRequest;
import com.yalcin.Request.Impl.ProductRequestImpl;
import com.yalcin.DTO.Product;
@Entity
@Table(name = "showcase", schema = "public")
public class Showcase {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Transient
 private  User user;

@Transient
 private  Product product;

@Column(name = "enabled")
 private  boolean enabled;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "ende_time")
 private  Date endTime;

@Size(min = 1, max = 50)
@Column(name = "price")
 private  float price;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Integer id;

@Transient
 private ProductRequest productrequest = new ProductRequestImpl();;

public Showcase() {
}public Showcase(Date endTime, @Size(min = 1, max = 50) float price) {
    this.endTime = endTime;
    this.price = price;
}
public void setProduct(Product product){
 productrequest.setProduct(product,this.id);
}



public Product getProduct(){
  this.product = productrequest.getProduct(this.id);
return this.product;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public Integer getId(){
    return id;
}


public void setPrice(float price){
    this.price = price;
}


public float getPrice(){
    return price;
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public Date getEndTime(){
    return endTime;
}


public boolean isEnabled(){
    return enabled;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setId(Integer id){
    this.id = id;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



}