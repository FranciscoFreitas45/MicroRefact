package com.yalcin.DTO;
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
public class Order {

 private  Integer id;

 private  boolean enabled;

 private  Date timestamp;

 private  User user;

 private  Adress adress;

 private  Product product;

 private Integer id;

 private Integer id;

public Order() {
}
public Product getProduct(){
    return product;
}


public Date getTimestamp(){
    return timestamp;
}


public User getUser(){
    return user;
}


public Integer getId(){
    return id;
}


public Adress getAdress(){
    return adress;
}


}