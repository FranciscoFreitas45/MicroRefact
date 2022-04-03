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
@Table(name = "shop_item_info")
public class ShopItemInfo {

 private  long serialVersionUID;

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "book_id", nullable = false)
 private  Integer bookId;

@Column(name = "isbn", nullable = false)
 private  String isbn;

@Column(name = "shop_status", nullable = false)
 private  String shopStatus;

@Column(name = "shop_name", nullable = false)
 private  String shopName;

@Column(name = "gmt_create", updatable = false, nullable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modified", nullable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModified;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public ShopItemInfo() {
}
public Integer getVersion(){
    return this.version;
}


public String getShopStatus(){
    return this.shopStatus;
}


public void setGmtModified(Date gmtModified){
    this.gmtModified = gmtModified;
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


public String getIsbn(){
    return this.isbn;
}


public Date getGmtModified(){
    return this.gmtModified;
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


public void setShopStatus(String shopStatus){
    this.shopStatus = shopStatus;
}


public String getShopName(){
    return this.shopName;
}


public Integer getBookId(){
    return this.bookId;
}


public void setId(Integer id){
    this.id = id;
}


public void setShopName(String shopName){
    this.shopName = shopName;
}


}