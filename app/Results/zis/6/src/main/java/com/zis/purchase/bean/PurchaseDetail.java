package com.zis.purchase.bean;
 import java.sql.Timestamp;
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
@Table(name = "purchase_detail")
public class PurchaseDetail {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "bookId", nullable = false)
 private  Integer bookId;

@Column(name = "purchasedAmount", nullable = false)
 private  Integer purchasedAmount;

@Column(name = "inwarehouse_amount", nullable = false)
 private  Integer inwarehouseAmount;

@Column(name = "batchId")
 private  Integer batchId;

@Column(name = "memo")
 private  String memo;

@Column(name = "status", nullable = false)
 private  String status;

@Column(name = "operator", nullable = false)
 private  String operator;

@Column(name = "position", nullable = false)
 private  String position;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "GMT_CREATE", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "GMT_MODIFY", nullable = false)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public PurchaseDetail() {
}/**
 * full constructor
 */
public PurchaseDetail(Integer bookId, Integer purchasedAmount, Integer batchId, String memo, String status, String operator, Timestamp gmtCreate, Timestamp gmtModify, Integer version) {
    this.bookId = bookId;
    this.purchasedAmount = purchasedAmount;
    this.batchId = batchId;
    this.memo = memo;
    this.status = status;
    this.operator = operator;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public Integer getVersion(){
    return this.version;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setPurchasedAmount(Integer purchasedAmount){
    this.purchasedAmount = purchasedAmount;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getInwarehouseAmount(){
    return inwarehouseAmount;
}


public Integer getId(){
    return this.id;
}


public void setInwarehouseAmount(Integer inwarehouseAmount){
    this.inwarehouseAmount = inwarehouseAmount;
}


public void setBatchId(Integer batchId){
    this.batchId = batchId;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getStatus(){
    return this.status;
}


public Integer getPurchasedAmount(){
    return this.purchasedAmount;
}


public void setStatus(String status){
    this.status = status;
}


public void setPosition(String position){
    this.position = position;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getPosition(){
    return position;
}


public Integer getBookId(){
    return this.bookId;
}


public Integer getBatchId(){
    return this.batchId;
}


public void setId(Integer id){
    this.id = id;
}


public void setOperator(String operator){
    this.operator = operator;
}


public String getOperator(){
    return this.operator;
}


}