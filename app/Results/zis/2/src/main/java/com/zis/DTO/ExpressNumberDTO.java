package com.zis.DTO;
 public class ExpressNumberDTO {

 private  Integer orderId;

 private  String receiverName;

 private  String expressNumber;

 private  String expressCompany;


public String getExpressCompany(){
    return expressCompany;
}


public Integer getOrderId(){
    return orderId;
}


public String getExpressNumber(){
    return expressNumber;
}


public String getReceiverName(){
    return receiverName;
}


}