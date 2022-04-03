package com.zis.requirement.dto;
 public class BookRequireUploadDTO {

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookEdition;

 private  String bookPublisher;

 private  String college;

 private  String institute;

 private  String partName;

 private  String classNum;

 private  Integer grade;

 private  Integer term;

 private  Integer amount;


public void setCollege(String college){
    this.college = college;
}


public String getInstitute(){
    return institute;
}


public void setPartName(String partName){
    this.partName = partName;
}


public void setInstitute(String institute){
    this.institute = institute;
}


public String getCollege(){
    return college;
}


public Integer getTerm(){
    return term;
}


public void setClassNum(String classNum){
    this.classNum = classNum;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
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


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public String getBookName(){
    return bookName;
}


public void setGrade(Integer grade){
    this.grade = grade;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public String getClassNum(){
    return classNum;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public String getPartName(){
    return partName;
}


public void setTerm(Integer term){
    this.term = term;
}


public String getBookPublisher(){
    return bookPublisher;
}


public Integer getGrade(){
    return grade;
}


public Integer getAmount(){
    return amount;
}


}