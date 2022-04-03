package com.zis.oldapi.response;
 public class RequiredAmountQueryData {

 private  Integer bookId;

 private  String bookName;

 private  Integer requireAmount;

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


public void setRequireAmount(Integer requireAmount){
    this.requireAmount = requireAmount;
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


public String getBookPublisher(){
    return bookPublisher;
}


}