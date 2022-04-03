package com.zis.toolkit.controller;
 import com.zis.purchase.bean.TempImportDetail;
public class StockPosCheckRecord extends TempImportDetail{

 private  String bookName;


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


}