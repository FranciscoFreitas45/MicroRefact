package com.zis.requirement.bean;
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
@Table(name = "book_require_import_task")
public class BookRequireImportTask {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "college", length = 32, nullable = false)
 private  String college;

@Column(name = "operator", length = 32, nullable = false)
 private  String operator;

@Column(name = "memo", length = 128, nullable = false)
 private  String memo;

@Column(name = "total_count")
 private  Integer totalCount;

@Column(name = "status", length = 32, nullable = false)
 private  String status;

@Column(name = "gmt_create", length = 19, nullable = false, updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify", length = 19, nullable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version", nullable = false)
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public BookRequireImportTask() {
}/**
 * minimal constructor
 */
public BookRequireImportTask(String operator, String memo, String status, Date gmtCreate, Date gmtModify, Integer version) {
    this.operator = operator;
    this.memo = memo;
    this.status = status;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}/**
 * full constructor
 */
public BookRequireImportTask(String operator, String memo, Integer totalCount, String status, Date gmtCreate, Date gmtModify, Integer version) {
    this.operator = operator;
    this.memo = memo;
    this.totalCount = totalCount;
    this.status = status;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public void setCollege(String college){
    this.college = college;
}


public Integer getVersion(){
    return version;
}


public void setTotalCount(Integer totalCount){
    this.totalCount = totalCount;
}


public String getCollege(){
    return college;
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getTotalCount(){
    return totalCount;
}


public Integer getId(){
    return id;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getStatus(){
    return status;
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


public String getMemo(){
    return memo;
}


public void setId(Integer id){
    this.id = id;
}


public void setOperator(String operator){
    this.operator = operator;
}


public String getOperator(){
    return operator;
}


}