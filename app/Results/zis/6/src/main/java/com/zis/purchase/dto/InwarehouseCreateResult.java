package com.zis.purchase.dto;
 public class InwarehouseCreateResult {

 private  boolean isSuccess;

 private  Integer inwarehouseId;

 private  String failReason;

 private  String currentPosition;


public String getFailReason(){
    return failReason;
}


public void setSuccess(boolean isSuccess){
    this.isSuccess = isSuccess;
}


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public String getCurrentPosition(){
    return currentPosition;
}


public void setCurrentPosition(String currentPosition){
    this.currentPosition = currentPosition;
}


public Integer getInwarehouseId(){
    return inwarehouseId;
}


public boolean isSuccess(){
    return isSuccess;
}


}