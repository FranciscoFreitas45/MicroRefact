package com.zis.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getReceiverPhone(){
    return receiverPhone;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public Integer getItemId(){
    return itemId;
}


public String getBuyerMessage(){
    return buyerMessage;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public Double getOrderMoney(){
    return orderMoney;
}


public Integer getOperator(){
    return operator;
}


public String getOrderType(){
    return orderType;
}


public Integer getShopId(){
    return shopId;
}


public String getSalerRemark(){
    return salerRemark;
}


public String getItemName(){
    return itemName;
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


public String getReceiverName(){
    return receiverName;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOutOrderNumber"))

.queryParam("outOrderNumber",outOrderNumber)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrderMoney(Double orderMoney){
    this.orderMoney = orderMoney;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderMoney"))

.queryParam("orderMoney",orderMoney)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrderType(String orderType){
    this.orderType = orderType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderType"))

.queryParam("orderType",orderType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShopId"))

.queryParam("shopId",shopId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiverAddr"))

.queryParam("receiverAddr",receiverAddr)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiverName"))

.queryParam("receiverName",receiverName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiverPhone"))

.queryParam("receiverPhone",receiverPhone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSalerRemark(String salerRemark){
    this.salerRemark = salerRemark;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSalerRemark"))

.queryParam("salerRemark",salerRemark)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBuyerMessage(String buyerMessage){
    this.buyerMessage = buyerMessage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBuyerMessage"))

.queryParam("buyerMessage",buyerMessage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOperator(Integer operator){
    this.operator = operator;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOperator"))

.queryParam("operator",operator)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSubOrders(List<SubOrder> subOrders){
    this.subOrders = subOrders;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSubOrders"))

.queryParam("subOrders",subOrders)
;
restTemplate.put(builder.toUriString(),null);
}


}