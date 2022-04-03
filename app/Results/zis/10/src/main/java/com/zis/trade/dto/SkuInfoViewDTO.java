package com.zis.trade.dto;
 public class SkuInfoViewDTO {

 private  Integer skuId;

 private  String isbn;

 private  String itemName;

 private  Integer itemCount;

 private  Double zisPrice;

 private  Double itemPrice;

 private  Integer resultInt;


public Integer getResultInt(){
    return resultInt;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public Double getZisPrice(){
    return zisPrice;
}


public String getIsbn(){
    return isbn;
}


public void setZisPrice(Double zisPrice){
    this.zisPrice = zisPrice;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


public String getItemName(){
    return itemName;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public Double getItemPrice(){
    return itemPrice;
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
}


public void setResultInt(Integer resultInt){
    this.resultInt = resultInt;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}