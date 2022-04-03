package com.zis.storage.dto;
 import com.zis.bookinfo.bean.Bookinfo;
public class StorageProductViewDTO extends Bookinfo{

 private  long serialVersionUID;

 private  Integer skuId;

 private  Integer productId;

 private  Integer stockAmt;

 private  Integer stockOccupy;

 private  Integer stockAvailable;


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getSkuId(){
    return skuId;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public void setStockOccupy(Integer stockOccupy){
    this.stockOccupy = stockOccupy;
}


public void setStockAvailable(Integer stockAvailable){
    this.stockAvailable = stockAvailable;
}


public Integer getStockAmt(){
    return stockAmt;
}


public Integer getStockOccupy(){
    return stockOccupy;
}


public Integer getProductId(){
    return productId;
}


public void setStockAmt(Integer stockAmt){
    this.stockAmt = stockAmt;
}


public Integer getStockAvailable(){
    return stockAvailable;
}


}