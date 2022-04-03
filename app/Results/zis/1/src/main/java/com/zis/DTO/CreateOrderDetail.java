package com.zis.DTO;
 import java.util.List;
import com.zis.storage.entity.StorageOrder.OrderType;
public class CreateOrderDetail {

 private  Integer skuId;

 private  Integer amount;


public Integer getSkuId(){
    return skuId;
}


public Integer getAmount(){
    return amount;
}


}