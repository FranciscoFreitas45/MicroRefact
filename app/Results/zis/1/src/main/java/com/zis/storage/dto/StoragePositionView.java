package com.zis.storage.dto;
 import com.zis.storage.entity.StoragePosition;
public class StoragePositionView extends StoragePosition{

 private  Integer lastAmount;


public Integer getLastAmount(){
    return lastAmount;
}


public void setLastAmount(Integer lastAmount){
    this.lastAmount = lastAmount;
}


}