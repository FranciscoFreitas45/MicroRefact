package com.zis.DTO;
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


public OrderType getOrderType(){
    return orderType;
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


public Integer getOutOrderId(){
    return outOrderId;
}


public List<CreateOrderDetail> getDetailList(){
    return detailList;
}


public Integer getAmount(){
    return amount;
}


}