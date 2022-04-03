package com.yalcin.entity;
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
@Entity
@Table(name = "product", schema = "public")
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Size(min = 3, max = 50)
@NotNull
@Column(name = "product_name")
 private  String productName;

@Column(name = "enabled")
 private  boolean enabled;

@Column(name = "showcase_enabled")
 private  boolean showcaseEnabled;

@Size(min = 15, max = 250)
@Column(name = "explanation")
@NotNull
 private  String explanation;

@Column(name = "product_image")
@Lob
 private  String productImage;

@Column(name = "file_product_image")
 private  File fileProductImage;

@Size(min = 1, max = 50)
@Column(name = "price")
 private  float price;

@Size(min = 1, max = 250)
@Column(name = "stock")
@NotNull
 private  Integer stock;

 private  Date timestap;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
 private  Set<Category> categorys;

@Transient
 private  User user;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Product(@Size(min = 3, max = 50) @NotNull String productName, @Size(min = 15, max = 250) @NotNull String explanation, @Size(min = 1, max = 50) float price, @Size(min = 1, max = 250) @NotNull Integer stock) {
    this.productName = productName;
    this.explanation = explanation;
    this.price = price;
    this.stock = stock;
}public Product() {
}
public void setStock(Integer stock){
    this.stock = stock;
}


public Date getTimestap(){
    return timestap;
}


public void setCategorys(Set<Category> categorys){
    this.categorys = categorys;
}


public void setExplanation(String explanation){
    this.explanation = explanation;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setProductName(String productName){
    this.productName = productName;
}


public Integer getStock(){
    return stock;
}


public Integer getId(){
    return id;
}


public void setPrice(float price){
    this.price = price;
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


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public void setTimestap(Date timestap){
    this.timestap = timestap;
}


public void setProductImage(String productImage){
    this.productImage = productImage;
}


public void setFileProductImage(File fileProductImage){
    this.fileProductImage = fileProductImage;
}


public boolean isEnabled(){
    return enabled;
}


public void setId(Integer id){
    this.id = id;
}


public void setShowcaseEnabled(boolean showcaseEnabled){
    this.showcaseEnabled = showcaseEnabled;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public Set<Category> getCategorys(){
    return categorys;
}


public File getFileProductImage(){
    return fileProductImage;
}


public boolean isShowcaseEnabled(){
    return showcaseEnabled;
}


}