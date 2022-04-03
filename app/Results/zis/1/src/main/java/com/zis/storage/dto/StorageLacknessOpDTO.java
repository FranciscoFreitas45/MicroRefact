package com.zis.storage.dto;
 import com.zis.storage.entity.StorageIoDetail;
public class StorageLacknessOpDTO extends StorageIoDetail{

 private  boolean lacknessMatchNewPos;

 private  Integer lackOutOrderId;

 private  boolean hasNext;


public void setLacknessMatchNewPos(boolean lacknessMatchNewPos){
    this.lacknessMatchNewPos = lacknessMatchNewPos;
}


public boolean getLacknessMatchNewPos(){
    return lacknessMatchNewPos;
}


public void setLackOutOrderId(Integer lackOutOrderId){
    this.lackOutOrderId = lackOutOrderId;
}


public Integer getLackOutOrderId(){
    return lackOutOrderId;
}


public boolean isHasNext(){
    return hasNext;
}


public void setHasNext(boolean hasNext){
    this.hasNext = hasNext;
}


}