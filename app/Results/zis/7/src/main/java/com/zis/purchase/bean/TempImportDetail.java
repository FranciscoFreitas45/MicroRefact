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
@Table(name = "temp_import_detail")
public class TempImportDetail {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "isbn")
 private  String isbn;

@Column(name = "orig_isbn", nullable = false)
 private  String origIsbn;

@Column(name = "data", nullable = false)
 private  String data;

@Column(name = "book_id", nullable = false)
 private  Integer bookId;

@Column(name = "task_id", nullable = false)
 private  Integer taskId;

@Column(name = "status", nullable = false)
 private  String status;

@Column(name = "additional_info")
 private  String additionalInfo;

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
public TempImportDetail() {
}
public Integer getTaskId(){
    return this.taskId;
}


public Integer getVersion(){
    return this.version;
}


public void setData(String data){
    this.data = data;
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public void setTaskId(Integer taskId){
    this.taskId = taskId;
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


public String getIsbn(){
    return this.isbn;
}


public String getStatus(){
    return this.status;
}


public void setAdditionalInfo(String additionalInfo){
    this.additionalInfo = additionalInfo;
}


public void setStatus(String status){
    this.status = status;
}


public String getAdditionalInfo(){
    return additionalInfo;
}


public void setOrigIsbn(String origIsbn){
    this.origIsbn = origIsbn;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getOrigIsbn(){
    return this.origIsbn;
}


public Integer getBookId(){
    return this.bookId;
}


public void setId(Integer id){
    this.id = id;
}


public String getData(){
    return data;
}


}