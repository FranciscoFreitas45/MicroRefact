package com.zis.purchase.dto;
 public class InwarehouseCreateDTO {

 private  String bizType;

 private  String purchaseOperator;

 private  String inwarehouseOperator;

 private  String[] stockPosLabel;

 private  Integer[] stockPosCapacity;

 private  String memo;


public void setInwarehouseOperator(String inwarehouseOperator){
    this.inwarehouseOperator = inwarehouseOperator;
}


public String[] getStockPosLabel(){
    return stockPosLabel;
}


public Integer[] getStockPosCapacity(){
    return stockPosCapacity;
}


public void setStockPosCapacity(Integer[] stockPosCapacity){
    this.stockPosCapacity = stockPosCapacity;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public void setStockPosLabel(String[] stockPosLabel){
    this.stockPosLabel = stockPosLabel;
}


public void setPurchaseOperator(String purchaseOperator){
    this.purchaseOperator = purchaseOperator;
}


public void setBizType(String bizType){
    this.bizType = bizType;
}


public String getInwarehouseOperator(){
    return inwarehouseOperator;
}


public String getBizType(){
    return bizType;
}


public String getPurchaseOperator(){
    return purchaseOperator;
}


}