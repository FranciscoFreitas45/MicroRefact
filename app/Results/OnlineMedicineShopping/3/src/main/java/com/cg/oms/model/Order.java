package com.cg.oms.model;
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
@Entity
@Component
@Table(name = "order_table")
public class Order {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long orderId;

@Transient
 private  User user;

@Transient
 private  Cart cart;

 private  LocalDateTime orderDate;

 private  String shippingAddress;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "cartId")
 private Long cartId;

@Transient
 private CartRequest cartrequest = new CartRequestImpl();;


public void setAddress(String shippingAddress){
    this.shippingAddress = shippingAddress;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
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
 cartrequest.setCart(cart,this.cartId);
}



public String getAddress(){
    return shippingAddress;
}


@Override
public String toString(){
    return "Order [orderId=" + orderId + ", user=" + user + ", orderDate=" + orderDate + ", Address=" + shippingAddress + "]";
}


public Cart getCart(){
  this.cart = cartrequest.getCart(this.cartId);
return this.cart;
}


public void setOrderId(Long orderId){
    this.orderId = orderId;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



}