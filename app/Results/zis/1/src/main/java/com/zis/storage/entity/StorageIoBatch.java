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
@Table(name = "storage_io_batch")
public class StorageIoBatch {

@Id
@GeneratedValue
@Column(name = "batch_id")
 private  Integer batchId;

@Column(name = "repo_id", nullable = false)
 private  Integer repoId;

@Column(name = "memo", nullable = false)
 private  String memo;

@Column(name = "operator")
 private  Integer operator;

@Column(name = "biz_type", nullable = false)
 private  String bizType;

@Column(name = "amount")
 private  Integer amount;

@Column(name = "status", nullable = false)
 private  String status;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_create", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_modify", nullable = false)
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


public Integer getVersion(){
    return this.version;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setBizType(String bizType){
    this.bizType = bizType;
}


public Date getGmtCreate(){
    return this.gmtCreate;
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


public BizType getBizType(String value){
    for (BizType bizType : BizType.values()) {
        if (bizType.getValue().equals(value)) {
            return bizType;
        }
    }
    return null;
}


public Status getStatus(String value){
    for (Status status : Status.values()) {
        if (status.getValue().equals(value)) {
            return status;
        }
    }
    return null;
}


public boolean isDailyStorage(BizType bizType){
    switch(bizType) {
        case IN_BATCH:
        case OUT_BATCH:
            return false;
        case IN_DAILY:
        case OUT_DAILY:
            return true;
        default:
            throw new RuntimeException("bizType不合法:" + bizType);
    }
}


public void setStatus(String status){
    this.status = status;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getValue(){
    return value;
}


public String getMemo(){
    return this.memo;
}


public boolean isInStorage(BizType bizType){
    switch(bizType) {
        case IN_BATCH:
        case IN_DAILY:
            return true;
        case OUT_BATCH:
        case OUT_DAILY:
            return false;
        default:
            throw new RuntimeException("bizType不合法:" + bizType);
    }
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public Integer getBatchId(){
    return batchId;
}


public void setOperator(Integer operator){
    this.operator = operator;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getOperator(){
    return operator;
}


public String getDisplay(){
    return display;
}


public Integer getAmount(){
    return this.amount;
}


}