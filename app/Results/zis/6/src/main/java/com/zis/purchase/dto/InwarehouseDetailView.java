package com.zis.purchase.dto;
 import com.zis.purchase.bean.InwarehouseDetail;
public class InwarehouseDetailView extends InwarehouseDetail{

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  String bookEdition;


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public String getBookName(){
    return bookName;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public String getBookEdition(){
    return bookEdition;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public String getIsbn(){
    return isbn;
}


public String getBookPublisher(){
    return bookPublisher;
}


}