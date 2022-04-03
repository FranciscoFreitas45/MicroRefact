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
@Table(name = "storage_io_detail")
public class StorageIoDetail {

 public  String SORT_POS_ID;

 public  String SORT_CREATE_TIME;

@Id
@GeneratedValue
@Column(name = "detail_id")
 private  Integer detailId;

@Column(name = "batch_id")
 private  Integer batchId;

@Column(name = "repo_id", nullable = false, updatable = false)
 private  Integer repoId;

@Column(name = "order_id")
 private  Integer orderId;

@Column(name = "pos_id", nullable = false, updatable = false)
 private  Integer posId;

@Column(name = "pos_label", nullable = false, updatable = false)
 private  String posLabel;

@Column(name = "sku_id", nullable = false, updatable = false)
 private  Integer skuId;

@Column(name = "product_id")
 private  Integer productId;

@Column(name = "amount", nullable = false)
 private  Integer amount;

@Column(name = "balance")
 private  Integer balance;

@Column(name = "operator", nullable = false)
 private  Integer operator;

@Column(name = "io_detail_type", nullable = false)
 private  String ioDetailType;

@Column(name = "detail_status", nullable = false)
 private  String detailStatus;

@Column(name = "gmt_create", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String display;

 private  String value;

 private  String display;


public Integer getRepoId(){
    return repoId;
}


public void setPosLabel(String posLabel){
    this.posLabel = posLabel;
}


public Integer getSkuId(){
    return this.skuId;
}


public void setPosId(Integer posId){
    this.posId = posId;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getOrderId(){
    return orderId;
}


public void setBatchId(Integer batchId){
    this.batchId = batchId;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public Integer getProductId(){
    return productId;
}


public void setDetailId(Integer detailId){
    this.detailId = detailId;
}


public void setOperator(Integer operator){
    this.operator = operator;
}


public void setDetailStatus(String detailStatus){
    this.detailStatus = detailStatus;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public void setBalance(Integer balance){
    this.balance = balance;
}


public Integer getDetailId(){
    return this.detailId;
}


public Integer getOperator(){
    return operator;
}


public Integer getAmount(){
    return this.amount;
}


public Integer getVersion(){
    return this.version;
}


public String getIoDetailType(){
    return ioDetailType;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public Integer getPosId(){
    return posId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public DetailStatus getDetailStatus(String status){
    for (DetailStatus st : DetailStatus.values()) {
        if (st.getValue().equals(status))
            return st;
    }
    return null;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getValue(){
    return value;
}


public Integer getBalance(){
    return balance;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public Integer getBatchId(){
    return batchId;
}


public void setIoDetailType(String ioDetailType){
    this.ioDetailType = ioDetailType;
}


public String getPosLabel(){
    return posLabel;
}


public String getDisplay(){
    return display;
}


public IoType getIoType(String type){
    for (IoType st : IoType.values()) {
        if (st.getValue().equals(type))
            return st;
    }
    return null;
}


public boolean isFinalStatus(String status){
    return isFinalStatus(getDetailStatus(status));
}


}