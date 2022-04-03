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
import org.springframework.format.annotation.DateTimeFormat;
public class Bookinfo {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer outId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Date publishDate;

 private  Double bookPrice;

 private  String bookEdition;

 private  Boolean isNewEdition;

 private  String groupId;

 private  String relateId;

 private  Boolean repeatIsbn;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private  String bookStatus;

// Constructors
/**
 * default constructor
 */
public Bookinfo() {
}/**
 * minimal constructor
 */
public Bookinfo(String isbn, String bookName, String bookAuthor, String bookPublisher, Double bookPrice) {
    this.isbn = isbn;
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.bookPublisher = bookPublisher;
    this.bookPrice = bookPrice;
}/**
 * full constructor
 */
public Bookinfo(Integer outId, String isbn, String bookName, String bookAuthor, String bookPublisher, Date publishDate, Double bookPrice, String bookEdition, Boolean isNewEdition, String groupId, String relateId, Boolean repeatIsbn, Date gmtCreate, Date gmtModify, Integer version, String bookStatus) {
    this.outId = outId;
    this.isbn = isbn;
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.bookPublisher = bookPublisher;
    this.publishDate = publishDate;
    this.bookPrice = bookPrice;
    this.bookEdition = bookEdition;
    this.isNewEdition = isNewEdition;
    this.groupId = groupId;
    this.relateId = relateId;
    this.repeatIsbn = repeatIsbn;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
    this.bookStatus = bookStatus;
}
public String getRelateId(){
    return this.relateId;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getId(){
    return this.id;
}


public Date getPublishDate(){
    return this.publishDate;
}


public String getGroupId(){
    return this.groupId;
}


public String getBookName(){
    return this.bookName;
}


public Integer getVersion(){
    return version;
}


public Integer getOutId(){
    return this.outId;
}


public Double getBookPrice(){
    return this.bookPrice;
}


public String getBookEdition(){
    return bookEdition;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public String getBookStatus(){
    return this.bookStatus;
}


public String getIsbn(){
    return this.isbn;
}


public String getBookAuthor(){
    return this.bookAuthor;
}


public Boolean getIsNewEdition(){
    return this.isNewEdition;
}


public Boolean getRepeatIsbn(){
    return this.repeatIsbn;
}


public String getBookPublisher(){
    return this.bookPublisher;
}


}