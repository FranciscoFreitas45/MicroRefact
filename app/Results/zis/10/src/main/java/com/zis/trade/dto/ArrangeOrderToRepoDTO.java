package com.zis.trade.dto;
 import java.util.List;
import com.zis.storage.entity.StorageRepoInfo;
public class ArrangeOrderToRepoDTO {

 private  Integer orderId;

 private  List<StorageRepoInfo> repoList;

 private  String forwardUrl;

 private  boolean success;

 private  String failReason;


public String getFailReason(){
    return failReason;
}


public List<StorageRepoInfo> getRepoList(){
    return repoList;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String getForwardUrl(){
    return forwardUrl;
}


public void setForwardUrl(String forwardUrl){
    this.forwardUrl = forwardUrl;
}


public Integer getOrderId(){
    return orderId;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public void setRepoList(List<StorageRepoInfo> repoList){
    this.repoList = repoList;
}


public boolean getSuccess(){
    return success;
}


}