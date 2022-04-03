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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

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


public void setUser(User user){
 userrequest.setUser(user,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdress(Adress adress){
 adressrequest.setAdress(adress,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdress"))

.queryParam("adress",adress)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEnabled"))

.queryParam("enabled",enabled)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTimestamp"))

.queryParam("timestamp",timestamp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProduct(Product product){
    this.product = product;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProduct"))

.queryParam("product",product)
;
restTemplate.put(builder.toUriString(),null);
}


}