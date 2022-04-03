package com.zis.trade.dto;
 import com.zis.bookinfo.bean.Bookinfo;
public class BookStorageProductDTO extends Bookinfo{

 private  long serialVersionUID;

 private  Integer bookAmount;


public Integer getBookAmount(){
    return bookAmount;
}


public void setBookAmount(Integer bookAmount){
    this.bookAmount = bookAmount;
}


}