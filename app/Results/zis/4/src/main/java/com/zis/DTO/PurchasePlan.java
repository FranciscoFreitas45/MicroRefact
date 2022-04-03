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
public class PurchasePlan {

 private  Integer id;

 private  Integer bookId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  String bookEdition;

 private  Integer requireAmount;

 private  Integer manualDecision;

 private  Integer repoId;

 private  Integer purchasedAmount;

 private  String status;

 private  String flag;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

// Constructors
/**
 * default constructor
 */
public PurchasePlan() {
}/**
 * full constructor
 */
public PurchasePlan(Integer id, Integer bookId, String isbn, String bookName, String bookAuthor, String bookPublisher, String bookEdition, Integer requireAmount, Integer manualDecision, Integer repoId, Integer purchasedAmount, String status, String flag, Date gmtCreate, Date gmtModify, Integer version) {
    super();
    this.id = id;
    this.bookId = bookId;
    this.isbn = isbn;
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.bookPublisher = bookPublisher;
    this.bookEdition = bookEdition;
    this.requireAmount = requireAmount;
    this.manualDecision = manualDecision;
    this.repoId = repoId;
    this.purchasedAmount = purchasedAmount;
    this.status = status;
    this.flag = flag;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public Integer getRepoId(){
    return repoId;
}


public void setManualDecision(Integer manualDecision){
    this.manualDecision = manualDecision;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getId(){
    return this.id;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getStatus(){
    return status;
}


public String getBookName(){
    return this.bookName;
}


public String getFlag(){
    return flag;
}


public Integer getVersion(){
    return this.version;
}


public void setVersion(Integer version){
    this.version = version;
}


public String getBookEdition(){
    return this.bookEdition;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public String getIsbn(){
    return this.isbn;
}


public Integer getPurchasedAmount(){
    return this.purchasedAmount;
}


public void setStatus(String status){
    this.status = status;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public String getBookAuthor(){
    return this.bookAuthor;
}


public Integer getManualDecision(){
    return manualDecision;
}


public Integer getBookId(){
    return this.bookId;
}


public Integer getRequireAmount(){
    return this.requireAmount;
}


public String getBookPublisher(){
    return this.bookPublisher;
}


}