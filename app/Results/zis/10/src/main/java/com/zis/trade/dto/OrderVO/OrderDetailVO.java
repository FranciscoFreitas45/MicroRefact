package com.zis.trade.dto.OrderVO;
 import java.util.Date;
import java.util.List;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.OrderDetail;
import com.zis.trade.processor.OrderHelper;
public class OrderDetailVO extends OrderDetail{

 private  Integer bookId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Date publishDate;

 private  Double bookPrice;

 private  String bookEdition;

 private  Boolean isNewEdition;


public Double getBookPrice(){
    return bookPrice;
}


public void setBookPrice(Double bookPrice){
    this.bookPrice = bookPrice;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public String getBookEdition(){
    return bookEdition;
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


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public Boolean getIsNewEdition(){
    return isNewEdition;
}


public Integer getBookId(){
    return bookId;
}


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public void setIsNewEdition(Boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public String getBookPublisher(){
    return bookPublisher;
}


}