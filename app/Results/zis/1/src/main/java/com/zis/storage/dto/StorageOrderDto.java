package com.zis.storage.dto;
 import java.util.List;
import com.zis.storage.entity.StorageOrder;
public class StorageOrderDto {

 private  List<OrderDetailDto> oList;

 private  StorageOrder storageOrder;


public void setStorageOrder(StorageOrder storageOrder){
    this.storageOrder = storageOrder;
}


public void setoList(List<OrderDetailDto> oList){
    this.oList = oList;
}


public List<OrderDetailDto> getoList(){
    return oList;
}


public StorageOrder getStorageOrder(){
    return storageOrder;
}


}