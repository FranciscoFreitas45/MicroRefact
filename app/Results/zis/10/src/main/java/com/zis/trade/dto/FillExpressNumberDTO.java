package com.zis.trade.dto;
 import com.zis.trade.entity.Order;
public class FillExpressNumberDTO extends Order{

 private  boolean success;

 private  String failReason;


public String getFailReason(){
    return failReason;
}


public void setSuccess(boolean success){
    this.success = success;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public boolean getSuccess(){
    return success;
}


}