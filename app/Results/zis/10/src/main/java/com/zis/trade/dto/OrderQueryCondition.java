package com.zis.trade.dto;
 import org.apache.commons.lang3.StringUtils;
public class OrderQueryCondition {

 private  Integer orderId;

 private  String outOrderNumber;

 private  String receiverName;

 private  String receiverPhone;

 private  String expressNumber;


public String getReceiverPhone(){
    if (StringUtils.isNotBlank(receiverPhone)) {
        return receiverPhone.trim();
    } else {
        return receiverPhone;
    }
}


public void setExpressNumber(String expressNumber){
    if (StringUtils.isNotBlank(expressNumber)) {
        this.expressNumber = expressNumber.trim();
    } else {
        this.expressNumber = expressNumber;
    }
}


public Integer getOrderId(){
    return orderId;
}


public String getExpressNumber(){
    if (StringUtils.isNotBlank(expressNumber)) {
        return expressNumber.trim();
    } else {
        return expressNumber;
    }
}


public void setReceiverName(String receiverName){
    if (StringUtils.isNotBlank(receiverName)) {
        this.receiverName = receiverName.trim();
    } else {
        this.receiverName = receiverName;
    }
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public void setOutOrderNumber(String outOrderNumber){
    if (StringUtils.isNotBlank(outOrderNumber)) {
        this.outOrderNumber = outOrderNumber.trim();
    } else {
        this.outOrderNumber = outOrderNumber;
    }
}


public String getReceiverName(){
    if (StringUtils.isNotBlank(receiverName)) {
        return receiverName.trim();
    } else {
        return receiverName;
    }
}


public String getOutOrderNumber(){
    if (StringUtils.isNotBlank(outOrderNumber)) {
        return outOrderNumber.trim();
    } else {
        return outOrderNumber;
    }
}


public void setReceiverPhone(String receiverPhone){
    if (StringUtils.isNotBlank(receiverPhone)) {
        this.receiverPhone = receiverPhone.trim();
    } else {
        this.receiverPhone = receiverPhone;
    }
}


}