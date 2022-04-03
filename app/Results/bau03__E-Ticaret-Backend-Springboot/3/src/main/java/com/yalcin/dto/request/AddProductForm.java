package com.yalcin.dto.request;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class AddProductForm {

@NotBlank
@Size(min = 3, max = 50)
 private  String productName;

@NotBlank
@Size(min = 3, max = 250)
 private  String explanation;

@NotBlank
 private  String productImage;

@NotBlank
@Size(min = 1, max = 250)
 private  String price;

@NotBlank
@Size(min = 1, max = 250)
 private  String stock;

 private  String productCategory;


public String getProductImage(){
    return productImage;
}


public void setProductCategory(String productCategory){
    this.productCategory = productCategory;
}


public void setStock(String stock){
    this.stock = stock;
}


public String getProductCategory(){
    return productCategory;
}


public void setProductImage(String productImage){
    this.productImage = productImage;
}


public void setExplanation(String explanation){
    this.explanation = explanation;
}


public void setProductName(String productName){
    this.productName = productName;
}


public String getStock(){
    return stock;
}


public void setPrice(String price){
    this.price = price;
}


public String getProductName(){
    return productName;
}


public String getPrice(){
    return price;
}


public String getExplanation(){
    return explanation;
}


}