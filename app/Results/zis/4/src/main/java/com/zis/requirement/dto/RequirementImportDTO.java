package com.zis.requirement.dto;
 public class RequirementImportDTO {

 private  String college;

 private  String institute;

 private  String partName;

 private  Integer count;

 private  Integer grade;

 private  Integer term;

 private  String isbn;

 private  String bookName;

 private  String failReason;


public void setCollege(String college){
    this.college = college;
}


public String getFailReason(){
    return failReason;
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


public String getIsbn(){
    return isbn;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public void setGrade(Integer grade){
    this.grade = grade;
}


public String getBookName(){
    return bookName;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


@Override
public String toString(){
    return "RequirementImportDTO [college=" + college + ", institute=" + institute + ", partName=" + partName + ", count=" + count + ", grade=" + grade + ", term=" + term + ", isbn=" + isbn + ", bookName=" + bookName + ", failReason=" + failReason + "]";
}


public Integer getCount(){
    return count;
}


public String getPartName(){
    return partName;
}


public void setCount(Integer count){
    this.count = count;
}


public void setTerm(Integer term){
    this.term = term;
}


public Integer getGrade(){
    return grade;
}


}