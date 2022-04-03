package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class ShopItemInfo {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer bookId;

 private  String isbn;

 private  String shopStatus;

 private  String shopName;

 private  Date gmtCreate;

 private  Date gmtModified;

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


public String getShopName(){
    return this.shopName;
}


public Integer getBookId(){
    return this.bookId;
}


}