package com.zammc.domain.recharge;
 import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "recharge_order")
public class RechargeOrderEntity {

 private  long orderId;

 private  String userId;

 private  BigDecimal rechargeMoney;

 private  BigDecimal payMoney;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  int version;

 private  byte dataStatus;

 private  byte orderStatus;

 private  byte isPackage;


@Basic
@Column(name = "version")
public int getVersion(){
    return version;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setPayMoney(BigDecimal payMoney){
    this.payMoney = payMoney;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setVersion(int version){
    this.version = version;
}


@Basic
@Column(name = "is_package")
public byte getIsPackage(){
    return isPackage;
}


@Id
@Column(name = "order_id")
public long getOrderId(){
    return orderId;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "pay_money")
public BigDecimal getPayMoney(){
    return payMoney;
}


public void setOrderId(long orderId){
    this.orderId = orderId;
}


public void setIsPackage(byte isPackage){
    this.isPackage = isPackage;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Basic
@Column(name = "recharge_money")
public BigDecimal getRechargeMoney(){
    return rechargeMoney;
}


@Basic
@Column(name = "order_status")
public byte getOrderStatus(){
    return orderStatus;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setOrderStatus(byte orderStatus){
    this.orderStatus = orderStatus;
}


public void setRechargeMoney(BigDecimal rechargeMoney){
    this.rechargeMoney = rechargeMoney;
}


@Basic
@Column(name = "user_id")
public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}