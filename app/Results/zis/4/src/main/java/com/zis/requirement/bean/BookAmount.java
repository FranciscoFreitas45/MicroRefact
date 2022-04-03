package com.zis.requirement.bean;
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
@Entity
@Table(name = "bookamount")
@DataTransferObject
public class BookAmount {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "bookId", nullable = false)
 private  Integer bookId;

@Column(name = "ISBN", nullable = false, length = 50)
 private  String isbn;

@Column(name = "bookName", nullable = false, length = 50)
 private  String bookName;

@Column(name = "bookAuthor", nullable = false, length = 50)
 private  String bookAuthor;

@Column(name = "bookPublisher", nullable = false)
 private  String bookPublisher;

@Column(name = "partId", nullable = false)
 private  Integer partId;

@Column(name = "amount", nullable = false)
 private  Integer amount;

@Column(name = "GMT_CREATE", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "GMT_MODIFY")
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

@Column(name = "operator", length = 110)
 private  String operator;

@Column(name = "college", length = 100)
 private  String college;

@Column(name = "institute", length = 100)
 private  String institute;

@Column(name = "partName", length = 100)
 private  String partName;

@Column(name = "grade")
 private  Integer grade;

@Column(name = "term")
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
public void setCollege(String college){
    this.college = college;
}


public String getInstitute(){
    return institute;
}


public void setPartName(String partName){
    this.partName = partName;
}


public String getCollege(){
    return college;
}


public void setPartId(Integer partId){
    this.partId = partId;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getId(){
    return this.id;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public void setId(Integer id){
    this.id = id;
}


public String getBookName(){
    return this.bookName;
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


public void setInstitute(String institute){
    this.institute = institute;
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


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public Date getGmtModify(){
    return gmtModify;
}


public String getIsbn(){
    return this.isbn;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return this.bookAuthor;
}


public Integer getBookId(){
    return this.bookId;
}


@Override
public String toString(){
    return "BookAmount [id=" + id + ", bookId=" + bookId + ", isbn=" + isbn + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher=" + bookPublisher + ", partId=" + partId + ", amount=" + amount + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", version=" + version + ", operator=" + operator + ", college=" + college + ", institute=" + institute + ", partName=" + partName + ", grade=" + grade + ", term=" + term + "]";
}


public String getPartName(){
    return partName;
}


public void setTerm(Integer term){
    this.term = term;
}


public String getBookPublisher(){
    return this.bookPublisher;
}


}