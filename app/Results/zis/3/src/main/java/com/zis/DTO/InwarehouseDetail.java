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
public class InwarehouseDetail {

 private  Integer id;

 private  Integer inwarehouseId;

 private  String bizType;

 private  String positionLabel;

 private  Integer bookId;

 private  Integer amount;

 private  Date gmtCreate;

 private  Date gmtModify;

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


public void setVersion(Integer version){
    this.version = version;
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


public String getBizType(){
    return bizType;
}


public String getPositionLabel(){
    return this.positionLabel;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public Integer getBookId(){
    return this.bookId;
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