package com.cg.oms.vo;
 import java.io.Serializable;
import java.time.LocalDateTime;
import com.cg.oms.model.Cart;
import com.cg.oms.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import com.cg.oms.Interface.User;
import com.cg.oms.Interface.Cart;
@Data
public class OrderVo implements Serializable{

 private  Long serialVersionUID;

@JsonProperty(value = "orderId", access = Access.READ_ONLY)
 private  Long orderId;

 private  User user;

 private  Cart cart;

@JsonProperty(value = "orderDate")
 private  LocalDateTime orderDate;

@JsonProperty(value = "orderShippingAddress")
 private  String shippingAddress;


public String getShippingAddress(){
    return shippingAddress;
}


public void setShippingAddress(String shippingAddress){
    this.shippingAddress = shippingAddress;
}


public User getUser(){
    return user;
}


public void setOrderDate(LocalDateTime orderDate){
    this.orderDate = orderDate;
}


public Long getOrderId(){
    return orderId;
}


public LocalDateTime getOrderDate(){
    return orderDate;
}


public void setCart(Cart cart){
    this.cart = cart;
}


public Cart getCart(){
    return cart;
}


public void setOrderId(Long orderId){
    this.orderId = orderId;
}


public void setUser(User user){
    this.user = user;
}


}