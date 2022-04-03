package com.zis.trade.dto;
 public class SendOutViewDTO {

 private  boolean success;

 private  String failReason;

 private  OrderVO orderVO;


public String getFailReason(){
    return failReason;
}


public void setSuccess(boolean success){
    this.success = success;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public OrderVO getOrderVO(){
    return orderVO;
}


public void setOrderVO(OrderVO orderVO){
    this.orderVO = orderVO;
}


public boolean getSuccess(){
    return success;
}


}