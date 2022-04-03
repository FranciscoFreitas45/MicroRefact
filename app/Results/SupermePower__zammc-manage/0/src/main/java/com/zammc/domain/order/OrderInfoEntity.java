package com.zammc.domain.order;
 import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "order_info")
public class OrderInfoEntity {

 private  long orderId;

 private  String userId;

 private  BigDecimal totalprice;

 private  Long itemId;

 private  Byte payStatus;

 private  String payWay;

 private  Timestamp payTime;

 private  String tableNum;

 private  String memo;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  Byte version;

 private  Byte dataStatus;

 private  Byte isConfirm;


@Basic
@Column(name = "pay_time")
public Timestamp getPayTime(){
    return payTime;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setDataStatus(Byte dataStatus){
    this.dataStatus = dataStatus;
}


@Id
@Column(name = "order_id")
public long getOrderId(){
    return orderId;
}


public void setOrderId(long orderId){
    this.orderId = orderId;
}


public void setTotalprice(BigDecimal totalprice){
    this.totalprice = totalprice;
}


@Basic
@Column(name = "is_confirm")
public Byte getIsConfirm(){
    return isConfirm;
}


@Basic
@Column(name = "item_id")
public Long getItemId(){
    return itemId;
}


public void setPayWay(String payWay){
    this.payWay = payWay;
}


public void setIsConfirm(Byte isConfirm){
    this.isConfirm = isConfirm;
}


@Basic
@Column(name = "totalprice")
public BigDecimal getTotalprice(){
    return totalprice;
}


@Basic
@Column(name = "pay_way")
public String getPayWay(){
    return payWay;
}


public void setItemId(Long itemId){
    this.itemId = itemId;
}


public void setPayTime(Timestamp payTime){
    this.payTime = payTime;
}


@Basic
@Column(name = "version")
public Byte getVersion(){
    return version;
}


@Basic
@Column(name = "data_status")
public Byte getDataStatus(){
    return dataStatus;
}


public void setVersion(Byte version){
    this.version = version;
}


public void setTableNum(String tableNum){
    this.tableNum = tableNum;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "pay_status")
public Byte getPayStatus(){
    return payStatus;
}


public void setPayStatus(Byte payStatus){
    this.payStatus = payStatus;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Basic
@Column(name = "memo")
public String getMemo(){
    return memo;
}


@Basic
@Column(name = "table_num")
public String getTableNum(){
    return tableNum;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
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