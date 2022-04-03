package com.zis.shop.dto;
 import java.util.Date;
public class ApplyRefundDTO {

 private  Integer shopId;

 private  String outOrderNumber;

 private  Integer operator;

 private  Date applyTime;

 private  String refundMemo;


public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setRefundMemo(String refundMemo){
    this.refundMemo = refundMemo;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public Integer getShopId(){
    return shopId;
}


public String getRefundMemo(){
    return refundMemo;
}


public void setOperator(Integer operator){
    this.operator = operator;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public Integer getOperator(){
    return operator;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


}