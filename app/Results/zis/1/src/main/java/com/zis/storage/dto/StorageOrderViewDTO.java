package com.zis.storage.dto;
 import com.zis.storage.entity.StorageOrder;
public class StorageOrderViewDTO extends StorageOrder{

 private  String zhCnStatus;


public String getZhCnStatus(){
    return zhCnStatus;
}


public void setZhCnStatus(String zhCnStatus){
    this.zhCnStatus = zhCnStatus;
}


}