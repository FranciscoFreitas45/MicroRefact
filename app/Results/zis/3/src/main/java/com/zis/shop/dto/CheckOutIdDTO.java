package com.zis.shop.dto;
 import com.zis.bookinfo.bean.Bookinfo;
public class CheckOutIdDTO {

 private  Bookinfo book;

 private  boolean isSuccess;

 private  String failMsg;


public boolean getIsSuccess(){
    return isSuccess;
}


public void setIsSuccess(boolean isSuccess){
    this.isSuccess = isSuccess;
}


public Bookinfo getBook(){
    return book;
}


public void setBook(Bookinfo book){
    this.book = book;
}


public String getFailMsg(){
    return failMsg;
}


public void setFailMsg(String failMsg){
    this.failMsg = failMsg;
}


}