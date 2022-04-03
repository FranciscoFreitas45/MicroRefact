package com.cg.oms.DTO;
 import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import com.cg.oms.Request.UserRequest;
import com.cg.oms.Request.Impl.UserRequestImpl;
import com.cg.oms.DTO.User;
import com.cg.oms.Request.CartRequest;
import com.cg.oms.Request.Impl.CartRequestImpl;
import com.cg.oms.DTO.Cart;
public class Order {

 private  Long orderId;

 private  User user;

 private  Cart cart;

 private  LocalDateTime orderDate;

 private  String shippingAddress;

 private Long userId;

 private Long cartId;


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public Long getOrderId(){
    return orderId;
}


public LocalDateTime getOrderDate(){
    return orderDate;
}


public String getAddress(){
    return shippingAddress;
}


public Cart getCart(){
  this.cart = cartrequest.getCart(this.cartId);
return this.cart;
}


}