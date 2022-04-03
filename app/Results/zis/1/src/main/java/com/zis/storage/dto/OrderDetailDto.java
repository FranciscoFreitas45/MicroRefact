package com.zis.storage.dto;
 public class OrderDetailDto {

 private  String bookTitle;

 private  Integer bookAmount;


public Integer getBookAmount(){
    return bookAmount;
}


public String getBookTitle(){
    return bookTitle;
}


public void setBookAmount(Integer bookAmount){
    this.bookAmount = bookAmount;
}


public void setBookTitle(String bookTitle){
    this.bookTitle = bookTitle;
}


}