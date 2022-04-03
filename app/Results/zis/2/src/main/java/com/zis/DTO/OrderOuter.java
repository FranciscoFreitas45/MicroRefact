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
public class OrderOuter {

 private  Integer outOrderId;

 private  Integer shopId;

 private  String shopName;

 private  String pName;

 private  String OrderData;

 private  String outOrderNumber;

 private  String orderGroupNumber;

 private  Date createTime;

 private  Date updateTime;

 private  Integer version;

// Constructors
/**
 * default constructor
 */
public OrderOuter() {
}
public Integer getVersion(){
    return this.version;
}


public Date getCreateTime(){
    return this.createTime;
}


public void setpName(String pName){
    this.pName = pName;
}


public Integer getShopId(){
    return this.shopId;
}


public String getpName(){
    return pName;
}


public void setOutOrderId(Integer outOrderId){
    this.outOrderId = outOrderId;
}


public String getOrderData(){
    return OrderData;
}


public Date getUpdateTime(){
    return this.updateTime;
}


public void setOrderGroupNumber(String orderGroupNumber){
    this.orderGroupNumber = orderGroupNumber;
}


public String getShopName(){
    return shopName;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public Integer getOutOrderId(){
    return this.outOrderId;
}


public String getOrderGroupNumber(){
    return this.orderGroupNumber;
}


public String getOutOrderNumber(){
    return this.outOrderNumber;
}


}