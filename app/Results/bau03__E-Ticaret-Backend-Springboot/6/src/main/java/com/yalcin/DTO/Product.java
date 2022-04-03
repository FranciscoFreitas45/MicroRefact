package com.yalcin.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import javax.persistence;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
public class Product {

 private  Integer id;

 private  String productName;

 private  boolean enabled;

 private  boolean showcaseEnabled;

 private  String explanation;

 private  String productImage;

 private  File fileProductImage;

 private  float price;

 private  Integer stock;

 private  Date timestap;

 private  Set<Category> categorys;

 private  User user;

 private Integer id;

public Product(@Size(min = 3, max = 50) @NotNull String productName, @Size(min = 15, max = 250) @NotNull String explanation, @Size(min = 1, max = 50) float price, @Size(min = 1, max = 250) @NotNull Integer stock) {
    this.productName = productName;
    this.explanation = explanation;
    this.price = price;
    this.stock = stock;
}public Product() {
}
public Date getTimestap(){
    return timestap;
}


public User getUser(){
    return user;
}


public Integer getStock(){
    return stock;
}


public Integer getId(){
    return id;
}


public String getProductName(){
    return productName;
}


public float getPrice(){
    return price;
}


public String getExplanation(){
    return explanation;
}


public String getProductImage(){
    return productImage;
}


public Set<Category> getCategorys(){
    return categorys;
}


public File getFileProductImage(){
    return fileProductImage;
}


}