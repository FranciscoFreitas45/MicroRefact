package com.zis.trade.dto.CreateOrderQuerySkuInfoViewDTO;
 import java.util.List;
import com.zis.bookinfo.bean.Bookinfo;
public class SkuInfo extends Bookinfo{

 private  long serialVersionUID;

 private  Integer bookAmount;

 private  Double zisPrice;


public Integer getBookAmount(){
    return bookAmount;
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


}