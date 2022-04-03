package com.zis.bookinfo.bean;
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
@Table(name = "youlu_sales")
public class YouluSales {

 private  long serialVersionUID;

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "bookId")
 private  Integer bookId;

@Column(name = "outId")
 private  Integer outId;

@Column(name = "stockBalance")
 private  Integer stockBalance;

@Column(name = "bookPrice", precision = 10, scale = 2)
 private  Double bookPrice;

@Column(name = "GMT_CREATE")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "GMT_MODIFY")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public YouluSales() {
}/**
 * minimal constructor
 */
public YouluSales(Integer stockBalance) {
    this.stockBalance = stockBalance;
}/**
 * full constructor
 */
public YouluSales(Integer bookId, Integer outId, Integer stockBalance, Double bookPrice, Date gmtCreate, Date gmtModify, Integer version) {
    this.bookId = bookId;
    this.outId = outId;
    this.stockBalance = stockBalance;
    this.bookPrice = bookPrice;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public Integer getVersion(){
    return this.version;
}


public Integer getOutId(){
    return this.outId;
}


public Double getBookPrice(){
    return this.bookPrice;
}


public void setBookPrice(Double bookPrice){
    this.bookPrice = bookPrice;
}


public void setVersion(Integer version){
    this.version = version;
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


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public Integer getStockBalance(){
    return this.stockBalance;
}


public Integer getBookId(){
    return this.bookId;
}


public void setId(Integer id){
    this.id = id;
}


public void setOutId(Integer outId){
    this.outId = outId;
}


public void setStockBalance(Integer stockBalance){
    this.stockBalance = stockBalance;
}


}