package com.zis.trade.dto;
 import java.util.List;
import com.zis.bookinfo.bean.Bookinfo;
public class CreateOrderQuerySkuInfoViewDTO {

 private  boolean success;

 private  String failReason;

 private  List<SkuInfo> skuList;

 private  long serialVersionUID;

 private  Integer bookAmount;

 private  Double zisPrice;


public String getFailReason(){
    return failReason;
}


public List<SkuInfo> getSkuList(){
    return skuList;
}


public Integer getBookAmount(){
    return bookAmount;
}


public void setSuccess(boolean success){
    this.success = success;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setBookAmount(Integer bookAmount){
    this.bookAmount = bookAmount;
}


public Double getZisPrice(){
    return zisPrice;
}


public void setZisPrice(Double zisPrice){
    this.zisPrice = zisPrice;
}


public boolean getSuccess(){
    return success;
}


public void setSkuList(List<SkuInfo> skuList){
    this.skuList = skuList;
}


}