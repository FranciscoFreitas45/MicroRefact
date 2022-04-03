package com.zis.api.response;
 public class RequiredAmountQueryDataV2 {

 private  Integer bookId;

 private  String bookName;

 private  Integer requireAmount;

 private  Integer stockAmount;

 private  boolean isNewEdition;

 private  String isbn;

 private  String bookAuthor;

 private  String bookPublisher;

 private  String bookEdition;

 private  String memo;


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
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


public Integer getStockAmount(){
    return stockAmount;
}


public void setRequireAmount(Integer requireAmount){
    this.requireAmount = requireAmount;
}


public void setNewEdition(boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public void setStockAmount(Integer stockAmount){
    this.stockAmount = stockAmount;
}


public Integer getBookId(){
    return bookId;
}


public String getBookName(){
    return bookName;
}


public Integer getRequireAmount(){
    return requireAmount;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public boolean isNewEdition(){
    return isNewEdition;
}


public String getBookPublisher(){
    return bookPublisher;
}


}