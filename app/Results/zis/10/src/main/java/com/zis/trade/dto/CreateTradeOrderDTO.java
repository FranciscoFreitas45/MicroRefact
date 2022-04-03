package com.zis.trade.dto;
 import java.util.List;
public class CreateTradeOrderDTO {

 private  Integer shopId;

 private  String outOrderNumber;

 private  String receiverName;

 private  String receiverPhone;

 private  String receiverAddr;

 private  Double orderMoney;

 private  String orderType;

 private  String salerRemark;

 private  String buyerMessage;

 private  Integer operator;

 private  List<SubOrder> subOrders;

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


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public void setSubOrders(List<SubOrder> subOrders){
    this.subOrders = subOrders;
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
    this.receiverPhone = receiverPhone;
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


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
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


public List<SubOrder> getSubOrders(){
    return subOrders;
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