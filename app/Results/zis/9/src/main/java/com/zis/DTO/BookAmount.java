package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.directwebremoting.annotations.DataTransferObject;
public class BookAmount {

 private  Integer id;

 private  Integer bookId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Integer partId;

 private  Integer amount;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private  String operator;

 private  String college;

 private  String institute;

 private  String partName;

 private  Integer grade;

 private  Integer term;

// Constructors
/**
 * default constructor
 */
public BookAmount() {
}/**
 * minimal constructor
 */
public BookAmount(Integer bookId, String isbn, String bookName, String bookAuthor, String bookPublisher, Integer partId, Integer amount) {
    this.bookId = bookId;
    this.isbn = isbn;
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.bookPublisher = bookPublisher;
    this.partId = partId;
    this.amount = amount;
}/**
 * full constructor
 */
public BookAmount(Integer bookId, String isbn, String bookName, String bookAuthor, String bookPublisher, Integer partId, Integer amount, Date gmtCreate, Date gmtModify, Integer version, String operator) {
    this.bookId = bookId;
    this.isbn = isbn;
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.bookPublisher = bookPublisher;
    this.partId = partId;
    this.amount = amount;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
    this.operator = operator;
}
public String getInstitute(){
    return institute;
}


public String getCollege(){
    return college;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getId(){
    return this.id;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getBookName(){
    return this.bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public String getOperator(){
    return this.operator;
}


public Integer getAmount(){
    return this.amount;
}


public Integer getGrade(){
    return grade;
}


public Integer getVersion(){
    return this.version;
}


public void setVersion(Integer version){
    this.version = version;
}


public Integer getPartId(){
    return this.partId;
}


public Integer getTerm(){
    return term;
}


public Date getGmtModify(){
    return gmtModify;
}


public String getIsbn(){
    return this.isbn;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public String getBookAuthor(){
    return this.bookAuthor;
}


public Integer getBookId(){
    return this.bookId;
}


public String getPartName(){
    return partName;
}


public String getBookPublisher(){
    return this.bookPublisher;
}


}