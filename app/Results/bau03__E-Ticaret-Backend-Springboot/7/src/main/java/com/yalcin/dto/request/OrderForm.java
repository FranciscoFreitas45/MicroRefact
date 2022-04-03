package com.yalcin.dto.request;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class OrderForm {

@NotBlank
@Size(min = 1, max = 250)
 private  String price;

@NotBlank
@Size(min = 1, max = 250)
 private  String adress;


public void setAdress(String adress){
    this.adress = adress;
}


public void setPrice(String price){
    this.price = price;
}


public String getPrice(){
    return price;
}


public String getAdress(){
    return adress;
}


}