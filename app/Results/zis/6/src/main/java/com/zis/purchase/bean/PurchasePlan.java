package com.zis.purchase.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "purchase_plan")
public class PurchasePlan {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "bookId", nullable = false)
 private  Integer bookId;

@Column(name = "isbn", nullable = false)
 private  String isbn;

@Column(name = "bookName", nullable = false, length = 100)
 private  String bookName;

@Column(name = "bookAuthor", nullable = false, length = 100)
 private  String bookAuthor;

@Column(name = "bookPublisher", nullable = false, length = 100)
 private  String bookPublisher;

@Column(name = "bookEdition", nullable = false, length = 100)
 private  String bookEdition;

@Column(name = "requireAmount", nullable = false)
 private  Integer requireAmount;

@Column(name = "manual_decision", nullable = false)
 private  Integer manualDecision;

@Column(name = "repo_id")
 private  Integer repoId;

@Column(name = "purchasedAmount", nullable = false)
 private  Integer purchasedAmount;

@Column(name = "status", nullable = false)
 private  String status;

@Column(name = "flag")
 private  String flag;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "GMT_CREATE", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "GMT_MODIFY", nullable = false)
 private  Date gmtModify;

@Version
@Column(name = "version", nullable = false)
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


public void setPurchasedAmount(Integer purchasedAmount){
    this.purchasedAmount = purchasedAmount;
}


public void setManualDecision(Integer manualDecision){
    this.manualDecision = manualDecision;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
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


public String getStatus(){
    return status;
}


public void setId(Integer id){
    this.id = id;
}


public String getBookName(){
    return this.bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public String getFlag(){
    return flag;
}


public Integer getVersion(){
    return this.version;
}


public void setFlag(String flag){
    this.flag = flag;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
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


public void setRequireAmount(Integer requireAmount){
    this.requireAmount = requireAmount;
}


public void setStatus(String status){
    this.status = status;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public String getBookAuthor(){
    return this.bookAuthor;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public Integer getManualDecision(){
    return manualDecision;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
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