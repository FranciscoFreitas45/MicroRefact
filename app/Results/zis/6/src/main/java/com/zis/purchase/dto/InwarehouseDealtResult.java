package com.zis.purchase.dto;
 import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class InwarehouseDealtResult {

 private  boolean success;

 private  String failReason;

 private  boolean positionChange;

 private  String prePosLabel;

 private  String curPosLabel;

 private  Integer totalAmount;


public boolean getPositionChange(){
    return positionChange;
}


public String getFailReason(){
    return failReason;
}


public void setSuccess(boolean success){
    this.success = success;
}


public void setTotalAmount(Integer totalAmount){
    this.totalAmount = totalAmount;
}


public Integer getTotalAmount(){
    return totalAmount;
}


public String getCurPosLabel(){
    return curPosLabel;
}


public void setPrePosLabel(String prePosLabel){
    this.prePosLabel = prePosLabel;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public String getPrePosLabel(){
    return prePosLabel;
}


public void setCurPosLabel(String curPosLabel){
    this.curPosLabel = curPosLabel;
}


public void setPositionChange(boolean positionChange){
    this.positionChange = positionChange;
}


public boolean getSuccess(){
    return success;
}


}