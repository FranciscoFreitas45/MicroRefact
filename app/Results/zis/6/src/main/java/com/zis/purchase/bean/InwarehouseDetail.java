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
@Table(name = "inwarehouse_detail")
public class InwarehouseDetail {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "inwarehouse_id", nullable = false)
 private  Integer inwarehouseId;

@Column(name = "biz_type", nullable = false)
 private  String bizType;

@Column(name = "position_label", nullable = false, length = 32)
 private  String positionLabel;

@Column(name = "book_id", nullable = false)
 private  Integer bookId;

@Column(name = "amount", nullable = false)
 private  Integer amount;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_create", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_modify", nullable = false)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public InwarehouseDetail() {
}
public Integer getVersion(){
    return this.version;
}


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setBizType(String bizType){
    this.bizType = bizType;
}


public void setPositionLabel(String positionLabel){
    this.positionLabel = positionLabel;
}


public Date getGmtCreate(){
    return this.gmtCreate;
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
    return bizType;
}


public String getPositionLabel(){
    return this.positionLabel;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public Integer getBookId(){
    return this.bookId;
}


public void setId(Integer id){
    this.id = id;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getInwarehouseId(){
    return this.inwarehouseId;
}


public Integer getAmount(){
    return this.amount;
}


}