package com.zis.DTO;
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
public class PurchaseDetail {

 private  Integer id;

 private  Integer bookId;

 private  Integer purchasedAmount;

 private  Integer inwarehouseAmount;

 private  Integer batchId;

 private  String memo;

 private  String status;

 private  String operator;

 private  String position;

 private  Date gmtCreate;

 private  Date gmtModify;

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


public void setBatchId(Integer batchId){
    this.batchId = batchId;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public String getStatus(){
    return this.status;
}


public Integer getPurchasedAmount(){
    return this.purchasedAmount;
}


public void setPosition(String position){
    this.position = position;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
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


public void setOperator(String operator){
    this.operator = operator;
}


public String getOperator(){
    return this.operator;
}


}