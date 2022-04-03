package com.zis.purchase.dto;
 public class StockDTO {

 private  Integer bookId;

 private  Integer stockBalance;


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public Integer getStockBalance(){
    return stockBalance;
}


public Integer getBookId(){
    return bookId;
}


public void setStockBalance(Integer stockBalance){
    this.stockBalance = stockBalance;
}


}