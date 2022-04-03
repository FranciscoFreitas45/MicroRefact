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
@Table(name = "temp_import_task")
public class TempImportTask {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "biz_type", nullable = false)
 private  String bizType;

@Column(name = "memo", length = 32)
 private  String memo;

@Column(name = "status", nullable = false)
 private  Integer status;

@Column(name = "total_count")
 private  Integer totalCount;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_create", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_modify", nullable = false)
 private  Date gmtModify;

@Version
@Column(name = "version", nullable = false)
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public TempImportTask() {
}/**
 * minimal constructor
 */
public TempImportTask(String bizType, Integer status, Timestamp gmtCreate, Timestamp gmtModify, Integer version) {
    this.bizType = bizType;
    this.status = status;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}/**
 * full constructor
 */
public TempImportTask(String bizType, String memo, Integer status, Date gmtCreate, Date gmtModify, Integer version) {
    this.bizType = bizType;
    this.memo = memo;
    this.status = status;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public Integer getVersion(){
    return this.version;
}


public void setTotalCount(Integer totalCount){
    this.totalCount = totalCount;
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


public Integer getTotalCount(){
    return totalCount;
}


public Integer getId(){
    return this.id;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getBizType(){
    return this.bizType;
}


public Integer getStatus(){
    return this.status;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setId(Integer id){
    this.id = id;
}


}