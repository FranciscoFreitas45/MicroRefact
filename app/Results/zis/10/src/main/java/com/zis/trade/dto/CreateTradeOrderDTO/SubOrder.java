package com.zis.trade.dto.CreateTradeOrderDTO;
 import java.util.List;
public class SubOrder {

 private  Integer itemId;

 private  String itemOutNum;

 private  Integer skuId;

 private  String itemName;

 private  Integer itemCount;

 private  Double itemPrice;


public Integer getItemId(){
    return itemId;
}


public String getItemName(){
    return itemName;
}


public String getItemOutNum(){
    return itemOutNum;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getSkuId(){
    return skuId;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
}


public Integer getItemCount(){
    return itemCount;
}


public Double getItemPrice(){
    return itemPrice;
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
}


public void setItemId(Integer itemId){
    this.itemId = itemId;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


}