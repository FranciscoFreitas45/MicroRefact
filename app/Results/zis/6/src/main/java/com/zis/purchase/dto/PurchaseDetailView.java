package com.zis.purchase.dto;
 import java.util.Date;
import com.zis.purchase.bean.PurchaseDetail;
public class PurchaseDetailView extends PurchaseDetail{

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  String bookEdition;

 private  Date publishDate;

 private  String statusDisplay;

 private  boolean isNewEdition;


public void setStatusDisplay(String statusDisplay){
    this.statusDisplay = statusDisplay;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public String getBookEdition(){
    return bookEdition;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public Date getPublishDate(){
    return publishDate;
}


public String getIsbn(){
    return isbn;
}


public void setNewEdition(boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public String getStatusDisplay(){
    return statusDisplay;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public boolean getIsNewEdition(){
    return isNewEdition;
}


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public String getBookPublisher(){
    return bookPublisher;
}


}