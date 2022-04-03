package com.zis.trade.dto;
 public class ChangeAddressDTO {

 private  String receiverName;

 private  String receiverPhone;

 private  String receiverAddr;


public String getReceiverPhone(){
    return receiverPhone;
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public String getReceiverName(){
    return receiverName;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
}


}