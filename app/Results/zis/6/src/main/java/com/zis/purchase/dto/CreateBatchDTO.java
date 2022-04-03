package com.zis.purchase.dto;
 import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
public class CreateBatchDTO {

@NotBlank(message = "入库类型必须选择")
 private  String bizType;

 private  String purchaseOperator;

@NotBlank(message = "入库操作员必须输入")
 private  String inwarehouseOperator;

@NotNull(message = "库位名称必须输入")
 private  String[] stockPosLabel;

@NotNull(message = "库位容量必须输入")
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


public void setBizType(String bizType){
    this.bizType = bizType;
}


public void setPurchaseOperator(String purchaseOperator){
    this.purchaseOperator = purchaseOperator;
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