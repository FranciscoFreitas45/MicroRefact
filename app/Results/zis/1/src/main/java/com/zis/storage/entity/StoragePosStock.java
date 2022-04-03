package com.zis.storage.entity;
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
@Table(name = "storage_pos_stock")
public class StoragePosStock {

@Id
@GeneratedValue
@Column(name = "stock_id")
 private  Integer stockId;

@Column(name = "pos_id")
 private  Integer posId;

@Column(name = "product_id")
 private  Integer productId;

@Column(name = "total_amt")
 private  Integer totalAmt;

@Column(name = "occupy_amt")
 private  Integer occupyAmt;

@Column(name = "gmt_create", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public StoragePosStock() {
}
public Integer getVersion(){
    return this.version;
}


public Integer getOccupyAmt(){
    return occupyAmt;
}


public void setOccupyAmt(Integer occupyAmt){
    this.occupyAmt = occupyAmt;
}


public Integer getPosId(){
    return posId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setStockId(Integer stockId){
    this.stockId = stockId;
}


public void setPosId(Integer posId){
    this.posId = posId;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getTotalAmt(){
    return totalAmt;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public Integer getStockId(){
    return stockId;
}


public Integer getProductId(){
    return productId;
}


public void setTotalAmt(Integer totalAmt){
    this.totalAmt = totalAmt;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


}