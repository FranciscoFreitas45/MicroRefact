package com.zis.shop.dto;
 public class ShopDownLoadFailDTO {

 private  String title;

 private  String outerId;

 private  String failReason;


public String getFailReason(){
    return failReason;
}


public String getTitle(){
    return title;
}


public void setTitle(String title){
    this.title = title;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public String getOuterId(){
    return outerId;
}


public void setOuterId(String outerId){
    this.outerId = outerId;
}


}