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
public class TempImportTask {

 private  Integer id;

 private  String bizType;

 private  String memo;

 private  Integer status;

 private  Integer totalCount;

 private  Date gmtCreate;

 private  Date gmtModify;

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


public void setVersion(Integer version){
    this.version = version;
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


public String getBizType(){
    return this.bizType;
}


public Integer getStatus(){
    return this.status;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getMemo(){
    return this.memo;
}


}