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
@Table(name = "inwarehouse_position")
public class InwarehousePosition {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "inwarehouse_id", nullable = false)
 private  Integer inwarehouseId;

@Column(name = "position_label", nullable = false, length = 32)
 private  String positionLabel;

@Column(name = "capacity", nullable = false)
 private  Integer capacity;

@Column(name = "current_amount", nullable = false)
 private  Integer currentAmount;

@Column(name = "is_full", nullable = false)
 private  boolean isFull;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_create", nullable = false, updatable = false)
 private  Date gmtCreate;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "gmt_modify", nullable = false)
 private  Date gmtModify;

@Version
@Column(name = "", nullable = false)
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public InwarehousePosition() {
}
public Integer getVersion(){
    return this.version;
}


public Integer getCurrentAmount(){
    return this.currentAmount;
}


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
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


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getPositionLabel(){
    return this.positionLabel;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setIsFull(boolean isFull){
    this.isFull = isFull;
}


public boolean getIsFull(){
    return this.isFull;
}


public void setId(Integer id){
    this.id = id;
}


public void setCapacity(Integer capacity){
    this.capacity = capacity;
}


public Integer getInwarehouseId(){
    return this.inwarehouseId;
}


public void setCurrentAmount(Integer currentAmount){
    this.currentAmount = currentAmount;
}


public Integer getCapacity(){
    return this.capacity;
}


}