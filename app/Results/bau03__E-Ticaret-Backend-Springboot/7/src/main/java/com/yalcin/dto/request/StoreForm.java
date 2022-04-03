package com.yalcin.dto.request;
 import javax.persistence.Column;
import javax.validation.constraints.Size;
public class StoreForm {

@Size(min = 1, max = 50)
@Column(name = "balance")
 private  String productId;


public void setProductId(String productId){
    this.productId = productId;
}


public String getProductId(){
    return productId;
}


}