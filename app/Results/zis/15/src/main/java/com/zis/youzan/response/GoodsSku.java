package com.zis.youzan.response;
 import com.alibaba.fastjson.annotation.JSONField;
public class GoodsSku {

@JSONField(name = "num_iid")
 private  Long numIid;

@JSONField(name = "quantity")
 private  Number quantity;

@JSONField(name = "price")
 private  Double price;

@JSONField(name = "sku_id")
 private  Long skuId;


public Number getQuantity(){
    return quantity;
}


public void setNumIid(Long numIid){
    this.numIid = numIid;
}


public void setSkuId(Long skuId){
    this.skuId = skuId;
}


public void setQuantity(Number quantity){
    this.quantity = quantity;
}


public Long getSkuId(){
    return skuId;
}


@Override
public String toString(){
    return "GoodsSku [numIid=" + numIid + ", quantity=" + quantity + ", price=" + price + ", skuId=" + skuId + "]";
}


public Long getNumIid(){
    return numIid;
}


public void setPrice(Double price){
    this.price = price;
}


public Double getPrice(){
    return price;
}


}