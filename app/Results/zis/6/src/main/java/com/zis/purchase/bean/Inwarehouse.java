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
@Table(name = "inwarehouse")
public class Inwarehouse {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "biz_type", nullable = false)
 private  String bizType;

@Column(name = "inwarehouse_operator", nullable = false)
 private  String inwarehouseOperator;

@Column(name = "source")
 private  String source;

@Column(name = "status", nullable = false)
 private  String status;

@Column(name = "amount", nullable = false)
 private  Integer amount;

@Column(name = "memo", length = 128)
 private  String memo;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_create", updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_modify")
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public Inwarehouse() {
}
public void setInwarehouseOperator(String inwarehouseOperator){
    this.inwarehouseOperator = inwarehouseOperator;
}


public Integer getVersion(){
    return this.version;
}


public void setSource(String source){
    this.source = source;
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


public Integer getId(){
    return this.id;
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


public String getBizType(){
    return bizType;
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
    return this.memo;
}


public String getSource(){
    return source;
}


public String getInwarehouseOperator(){
    return this.inwarehouseOperator;
}


public void setId(Integer id){
    this.id = id;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getAmount(){
    return this.amount;
}


}