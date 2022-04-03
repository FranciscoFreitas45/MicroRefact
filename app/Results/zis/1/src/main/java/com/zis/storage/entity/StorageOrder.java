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
@Table(name = "storage_order")
public class StorageOrder {

@Id
@GeneratedValue
@Column(name = "order_id")
 private  Integer orderId;

@Column(name = "out_order_id", nullable = false)
 private  Integer outOrderId;

@Column(name = "buyer_name", nullable = false)
 private  String buyerName;

@Column(name = "amount", nullable = false)
 private  Integer amount;

@Column(name = "order_detail", nullable = false)
 private  String orderDetail;

@Column(name = "repo_id", nullable = false)
 private  Integer repoId;

@Column(name = "shop_id")
 private  Integer shopId;

@Column(name = "order_type", nullable = false)
 private  String orderType;

@Column(name = "trade_status", nullable = false)
 private  String tradeStatus;

@Column(name = "gmt_create", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String display;

 private  String value;

 private  String display;


public Integer getRepoId(){
    return repoId;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public String getBuyerName(){
    return buyerName;
}


public void setTradeStatus(String tradeStatus){
    this.tradeStatus = tradeStatus;
}


public Integer getOrderId(){
    return orderId;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public void setOutOrderId(Integer outOrderId){
    this.outOrderId = outOrderId;
}


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public String getOrderDetail(){
    return orderDetail;
}


public String getTradeStatus(){
    return tradeStatus;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getAmount(){
    return amount;
}


public Integer getVersion(){
    return version;
}


public String getOrderType(){
    return orderType;
}


public Integer getShopId(){
    return shopId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setBuyerName(String buyerName){
    this.buyerName = buyerName;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getValue(){
    return value;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public Integer getOutOrderId(){
    return outOrderId;
}


public void setOrderDetail(String orderDetail){
    this.orderDetail = orderDetail;
}


public String getDisplay(){
    return display;
}


}