package com.hmm.finance.logisticst.domain;
 public class InStorageDetailedDTO {

 private  Long inStorageDetailedId;

 private  String goodsName;

 private  String unit;

 private  float price;

 private  float amount;

public InStorageDetailedDTO() {
    super();
}public InStorageDetailedDTO(Long inStorageDetailedId, String goodsName, String unit, float price, float amount) {
    super();
    this.inStorageDetailedId = inStorageDetailedId;
    this.goodsName = goodsName;
    this.unit = unit;
    this.price = price;
    this.amount = amount;
}
public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setInStorageDetailedId(Long inStorageDetailedId){
    this.inStorageDetailedId = inStorageDetailedId;
}


public Long getInStorageDetailedId(){
    return inStorageDetailedId;
}


public String getUnit(){
    return unit;
}


public void setPrice(float price){
    this.price = price;
}


public void setAmount(float amount){
    this.amount = amount;
}


public float getPrice(){
    return price;
}


public float getAmount(){
    return amount;
}


}