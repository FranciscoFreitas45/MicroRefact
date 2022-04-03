package com.zis.requirement.dto;
 public class GetAmountDTO {

 private  String isbn;

 private  String bookName;

 private  String school;

 private  String institute;

 private  String partName;

 private  Integer grade;

 private  Integer term;

 private  String operator;


public String getInstitute(){
    return institute;
}


public void setPartName(String partName){
    this.partName = partName;
}


public void setInstitute(String institute){
    this.institute = institute;
}


public Integer getTerm(){
    return term;
}


public String getIsbn(){
    return isbn;
}


public String getSchool(){
    return school;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
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


public void setOperator(String operator){
    this.operator = operator;
}


public void setSchool(String school){
    this.school = school;
}


public String getPartName(){
    return partName;
}


public void setTerm(Integer term){
    this.term = term;
}


public String getOperator(){
    return operator;
}


public Integer getGrade(){
    return grade;
}


}