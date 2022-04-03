package com.zis.storage.dto.CreateOrderDTO;
 import java.util.List;
import com.zis.storage.entity.StorageOrder.OrderType;
public class CreateOrderDetail {

 private  Integer skuId;

 private  Integer amount;


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getSkuId(){
    return skuId;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getAmount(){
    return amount;
}


}