package com.zis.trade.dto;
 public class ExpressNumberDTO {

 private  Integer orderId;

 private  String receiverName;

 private  String expressNumber;

 private  String expressCompany;


public String getExpressCompany(){
    return expressCompany;
}


public void setExpressNumber(String expressNumber){
    this.expressNumber = expressNumber;
}


public void setExpressCompany(String expressCompany){
    this.expressCompany = expressCompany;
}


public Integer getOrderId(){
    return orderId;
}


public String getExpressNumber(){
    return expressNumber;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public String getReceiverName(){
    return receiverName;
}


}