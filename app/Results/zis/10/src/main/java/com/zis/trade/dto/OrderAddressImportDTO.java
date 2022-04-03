package com.zis.trade.dto;
 public class OrderAddressImportDTO {

 private  String outOrderNumber;

 private  String receiverName;

 private  String receiverPhone;

 private  String receiverAddr;

public OrderAddressImportDTO() {
}public OrderAddressImportDTO(String outOrderNumber, String receiverName, String receiverPhone, String receiverAddr) {
    this.outOrderNumber = outOrderNumber;
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddr = receiverAddr;
}
public String getReceiverPhone(){
    return receiverPhone;
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public String getReceiverName(){
    return receiverName;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
}


}