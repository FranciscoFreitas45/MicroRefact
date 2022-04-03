package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
import com.yalcin.Request.AdressRequest;
import com.yalcin.Request.Impl.AdressRequestImpl;
import com.yalcin.DTO.Adress;
@Entity
@Table(name = "order_user", schema = "public")
public class Order {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

 private  boolean enabled;

 private  Date timestamp;

@Transient
 private  User user;

@Transient
 private  Adress adress;

@ManyToOne
@JoinColumn(name = "product_id", nullable = false)
@JsonIgnoreProperties(value = { "enabled" })
 private  Product product;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Integer id;

@Transient
 private AdressRequest adressrequest = new AdressRequestImpl();;

public Order() {
}
public void setAdress(Adress adress){
 adressrequest.setAdress(adress,this.id);
}



public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public void setProduct(Product product){
    this.product = product;
}


public Product getProduct(){
    return product;
}


public Date getTimestamp(){
    return timestamp;
}


public boolean isEnabled(){
    return enabled;
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



public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


public Adress getAdress(){
  this.adress = adressrequest.getAdress(this.id);
return this.adress;
}


}