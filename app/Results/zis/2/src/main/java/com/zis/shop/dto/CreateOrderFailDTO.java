package com.zis.shop.dto;
 public class CreateOrderFailDTO {

 private  boolean success;

 private  String failReason;

 private  String outOrderNumber;

 private  String receiverName;

 private  String receiverPhone;

 private  String itemId;

 private  String itemOutNum;


public String getFailReason(){
    return failReason;
}


public String getReceiverPhone(){
    return receiverPhone;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String getItemId(){
    return itemId;
}


public String getItemOutNum(){
    return itemOutNum;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setItemId(String itemId){
    this.itemId = itemId;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public String getReceiverName(){
    return receiverName;
}


public boolean isSuccess(){
    return success;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
}


}