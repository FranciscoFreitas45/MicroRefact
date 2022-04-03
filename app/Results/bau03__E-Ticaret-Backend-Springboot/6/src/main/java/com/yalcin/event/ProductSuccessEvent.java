package com.yalcin.event;
 import com.yalcin.entity.Product;
import com.yalcin.entity.User;
import org.springframework.context.ApplicationEvent;
import com.yalcin.Interface.User;
import com.yalcin.Interface.Product;
public class ProductSuccessEvent extends ApplicationEvent{

 private  long serialVersionUID;

 private  User user;

 private  Product product;

public ProductSuccessEvent(User user, Product product) {
    super(user);
    this.user = user;
    this.product = product;
}
public void setProduct(Product product){
    this.product = product;
}


public Product getProduct(){
    return product;
}


public User getUser(){
    return user;
}


public void setUser(User user){
    this.user = user;
}


}