package com.zis.trade.dto;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
public class OrderInfoDTO {

 private  Integer shopId;

 private  String outTradeNumber;

 private  String receiverName;

 private  String receiverPhone;

 private  String receiverAddr;

 private  Double orderMoney;

 private  String status;

 private  String orderType;

 private  String salerRemark;

 private  String buyerMessage;

 private  Integer operator;

 private  List<SkuInfo> skus;

 private  String outOrderNumber;

 private  Integer itemId;

 private  String itemOutNum;

 private  Integer skuId;

 private  String itemName;

 private  Integer itemCount;

 private  Double itemPrice;


public String getReceiverPhone(){
    return receiverPhone;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public String getStatus(){
    return status;
}


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public String getOutTradeNumber(){
    return outTradeNumber;
}


public Integer getItemId(){
    return itemId;
}


public String getBuyerMessage(){
    return buyerMessage;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
}


public void setOperator(Integer operator){
    this.operator = operator;
}


public void setItemId(Integer itemId){
    this.itemId = itemId;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public Double getOrderMoney(){
    return orderMoney;
}


public void setSalerRemark(String salerRemark){
    this.salerRemark = salerRemark;
}


public Integer getOperator(){
    return operator;
}


public void setReceiverPhone(String receiverPhone){
    if (StringUtils.isNotBlank(receiverPhone)) {
        this.receiverPhone = receiverPhone.replace("[']", "");
    } else {
        this.receiverPhone = receiverPhone;
    }
}


public String getOrderType(){
    return orderType;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getShopId(){
    return shopId;
}


public String getSalerRemark(){
    return salerRemark;
}


public void setSkus(List<SkuInfo> skus){
    this.skus = skus;
}


public List<SkuInfo> getSkus(){
    return skus;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public void setStatus(String status){
    this.status = status;
}


public void setOutTradeNumber(String outTradeNumber){
    this.outTradeNumber = outTradeNumber;
}


public void setOrderMoney(Double orderMoney){
    this.orderMoney = orderMoney;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


public String getItemName(){
    return itemName;
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public void setBuyerMessage(String buyerMessage){
    this.buyerMessage = buyerMessage;
}


public String getItemOutNum(){
    return itemOutNum;
}


public Double getItemPrice(){
    return itemPrice;
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public String getReceiverName(){
    return receiverName;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}