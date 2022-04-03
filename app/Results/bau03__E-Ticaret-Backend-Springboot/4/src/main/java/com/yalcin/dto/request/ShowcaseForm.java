package com.yalcin.dto.request;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
public class ShowcaseForm {

@NotBlank
@Size(min = 3, max = 50)
 private  String productId;

@NotBlank
@Size(min = 3, max = 250)
 private  Date endTime;

@NotBlank
@Size(min = 1, max = 250)
 private  String price;


public Date getEndTime(){
    return endTime;
}


public void setProductId(String productId){
    this.productId = productId;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setPrice(String price){
    this.price = price;
}


public String getPrice(){
    return price;
}


public String getProductId(){
    return productId;
}


}