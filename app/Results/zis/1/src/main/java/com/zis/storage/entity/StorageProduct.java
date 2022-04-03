package com.zis.storage.entity;
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
@Table(name = "storage_product")
public class StorageProduct {

@Id
@GeneratedValue
@Column(name = "product_id")
 private  Integer productId;

@Column(name = "sku_id", nullable = false)
 private  Integer skuId;

@Column(name = "sku_name", nullable = false)
 private  String skuName;

@Column(name = "repo_id", nullable = false)
 private  Integer repoId;

@Column(name = "subject_id", nullable = false)
 private  Integer subjectId;

@Column(name = "subject_name", nullable = false)
 private  String subjectName;

@Column(name = "stock_amt", nullable = false)
 private  Integer stockAmt;

@Column(name = "stock_occupy")
 private  Integer stockOccupy;

@Column(name = "stock_available")
 private  Integer stockAvailable;

@Column(name = "lock_flag")
 private  Boolean lockFlag;

@Column(name = "lock_reason")
 private  String lockReason;

@Column(name = "gmt_create", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public StorageProduct() {
}
public Integer getRepoId(){
    return repoId;
}


public Integer getSkuId(){
    return skuId;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getStockOccupy(){
    return stockOccupy;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public Boolean getLockFlag(){
    return lockFlag;
}


public Integer getProductId(){
    return productId;
}


public void setSubjectId(Integer subjectId){
    this.subjectId = subjectId;
}


public void setStockAmt(Integer stockAmt){
    this.stockAmt = stockAmt;
}


public void setSkuName(String skuName){
    this.skuName = skuName;
}


public void setSubjectName(String subjectName){
    this.subjectName = subjectName;
}


public void setLockReason(String lockReason){
    this.lockReason = lockReason;
}


public Integer getVersion(){
    return version;
}


public void setLockFlag(Boolean lockFlag){
    this.lockFlag = lockFlag;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public void setVersion(Integer version){
    this.version = version;
}


public Integer getSubjectId(){
    return subjectId;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public String getLockReason(){
    return lockReason;
}


public void setStockOccupy(Integer stockOccupy){
    this.stockOccupy = stockOccupy;
}


public Integer getStockAmt(){
    return stockAmt;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getSkuName(){
    return skuName;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public void setStockAvailable(Integer stockAvailable){
    this.stockAvailable = stockAvailable;
}


public String getSubjectName(){
    return subjectName;
}


public Integer getStockAvailable(){
    return stockAvailable;
}


}