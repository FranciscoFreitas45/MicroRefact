package com.zis.trade.dto;
 import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
public class CreateOrderViewDTO {

 private  boolean success;

 private  String failReason;

 private  String manualOrderType;

 private  String orderType;

@NotNull(message = "店铺Id不能为空")
 private  Integer shopId;

 private  Double discount;

@NotEmpty(message = "店铺订单号不能为为空")
 private  String outOrderNumber;

@NotEmpty(message = "收件人不能为为空")
 private  String receiverName;

@NotEmpty(message = "收件人电话不能为为空")
@Pattern(regexp = "^((13[0-9])|(17[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$", message = "手机号码格式不符")
 private  String receiverPhone;

@NotEmpty(message = "收件人地址不能为为空")
 private  String receiverAddr;

@NotNull(message = "邮费不能为空")
 private  Double postage;

@NotNull(message = "订单金额不能为空")
 private  Double orderMoney;

 private  String salerRemark;

 private  String buyerMessage;

 private  List<SkuViewInfo> skus;

 private  SkuInfoViewDTO skuOld;

 private  Integer skuNumber;

 private  long serialVersionUID;

 private  Integer skuId;

 private  String isbn;

 private  String itemName;

 private  Integer itemCount;

 private  Double zisPrice;

 private  Double itemPrice;

 private  Integer resultInt;


public String getReceiverPhone(){
    return receiverPhone;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public Double getZisPrice(){
    return zisPrice;
}


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public boolean getSuccess(){
    return success;
}


public Double getPostage(){
    return postage;
}


public String getBuyerMessage(){
    return buyerMessage;
}


public Double getDiscount(){
    return discount;
}


public void setSkuNumber(Integer skuNumber){
    this.skuNumber = skuNumber;
}


public void setDiscount(Double discount){
    this.discount = discount;
}


public void setResultInt(Integer resultInt){
    this.resultInt = resultInt;
}


public String getReceiverAddr(){
    return receiverAddr;
}


public Double getOrderMoney(){
    return orderMoney;
}


public void setSalerRemark(String salerRemark){
    this.salerRemark = salerRemark;
}


public String getManualOrderType(){
    return manualOrderType;
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
}


public String getFailReason(){
    return failReason;
}


public String getOrderType(){
    return orderType;
}


public Integer getResultInt(){
    return resultInt;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getShopId(){
    return shopId;
}


public String getSalerRemark(){
    return salerRemark;
}


public void setSkus(List<SkuViewInfo> skus){
    this.skus = skus;
}


public List<SkuViewInfo> getSkus(){
    return skus;
}


public void setManualOrderType(String manualOrderType){
    this.manualOrderType = manualOrderType;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
}


public String getIsbn(){
    return isbn;
}


public void setZisPrice(Double zisPrice){
    this.zisPrice = zisPrice;
}


public SkuInfoViewDTO getSkuOld(){
    return skuOld;
}


public void setSkuOld(SkuInfoViewDTO skuOld){
    this.skuOld = skuOld;
}


public Integer getSkuNumber(){
    return skuNumber;
}


public void setOrderMoney(Double orderMoney){
    this.orderMoney = orderMoney;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


public void setPostage(Double postage){
    this.postage = postage;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String getItemName(){
    return itemName;
}


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public void setBuyerMessage(String buyerMessage){
    this.buyerMessage = buyerMessage;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public Double getItemPrice(){
    return itemPrice;
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
}


public void setOutOrderNumber(String outOrderNumber){
    this.outOrderNumber = outOrderNumber;
}


public String getReceiverName(){
    return receiverName;
}


public String getOutOrderNumber(){
    return outOrderNumber;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}