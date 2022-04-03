package com.zis.trade.dto.OrderInfoDTO;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
public class SkuInfo {

 private  String outOrderNumber;

 private  Integer itemId;

 private  String itemOutNum;

 private  Integer skuId;

 private  String itemName;

 private  Integer itemCount;

 private  Double itemPrice;


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


public Integer getItemId(){
    return itemId;
}


public String getItemName(){
    return itemName;
}


public String getItemOutNum(){
    return itemOutNum;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
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


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}