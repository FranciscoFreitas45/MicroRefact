package com.zis.trade.dto;
 import org.apache.commons.lang3.StringUtils;
public class FillExpressNumberUploadDTO {

 private  Integer orderId;

 private  String receiverName;

 private  String expressNumber;

 private  String expressCompany;


public String getExpressCompany(){
    return expressCompany;
}


public void setExpressNumber(String expressNumber){
    this.expressNumber = expressNumber;
}


public void setExpressCompany(String expressCompany){
    if (StringUtils.isNotBlank(expressCompany)) {
        this.expressCompany = expressCompany.split(",")[0];
    } else {
        this.expressCompany = expressCompany;
    }
}


public Integer getOrderId(){
    return orderId;
}


public String getExpressNumber(){
    return expressNumber;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public String getReceiverName(){
    return receiverName;
}


}