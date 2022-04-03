package com.zis.storage.dto;
 import java.util.List;
import com.zis.storage.entity.StorageOrder.OrderType;
public class CreateOrderDTO {

 private  Integer repoId;

 private  Integer shopId;

 private  Integer outOrderId;

 private  String buyerName;

 private  OrderType orderType;

 private  List<CreateOrderDetail> detailList;

 private  Integer skuId;

 private  Integer amount;


public Integer getRepoId(){
    return repoId;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public OrderType getOrderType(){
    return orderType;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getShopId(){
    return shopId;
}


public Integer getSkuId(){
    return skuId;
}


public String getBuyerName(){
    return buyerName;
}


public void setBuyerName(String buyerName){
    this.buyerName = buyerName;
}


public void setOutOrderId(Integer outOrderId){
    this.outOrderId = outOrderId;
}


public void setOrderType(OrderType orderType){
    this.orderType = orderType;
}


public void setDetailList(List<CreateOrderDetail> detailList){
    this.detailList = detailList;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public Integer getOutOrderId(){
    return outOrderId;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public List<CreateOrderDetail> getDetailList(){
    return detailList;
}


public Integer getAmount(){
    return amount;
}


}