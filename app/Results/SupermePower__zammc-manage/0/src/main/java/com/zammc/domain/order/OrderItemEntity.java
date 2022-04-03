package com.zammc.domain.order;
 import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "order_item")
public class OrderItemEntity {

 private  long itemId;

 private  long orderId;

 private  String userId;

 private  long goodsId;

 private  String goodsType;

 private  String goodsName;

 private  int goodsNum;

 private  BigDecimal goodsPrice;

 private  BigDecimal realPrice;

 private  BigDecimal subtotal;

 private  byte orderType;

 private  Timestamp payTime;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  byte version;

 private  byte dataStatus;


@Basic
@Column(name = "goods_name")
public String getGoodsName(){
    return goodsName;
}


@Basic
@Column(name = "pay_time")
public Timestamp getPayTime(){
    return payTime;
}


public void setSubtotal(BigDecimal subtotal){
    this.subtotal = subtotal;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


@Basic
@Column(name = "order_id")
public long getOrderId(){
    return orderId;
}


public void setOrderId(long orderId){
    this.orderId = orderId;
}


public void setOrderType(byte orderType){
    this.orderType = orderType;
}


public void setGoodsNum(int goodsNum){
    this.goodsNum = goodsNum;
}


public void setGoodsType(String goodsType){
    this.goodsType = goodsType;
}


@Id
@Column(name = "item_id")
public long getItemId(){
    return itemId;
}


public void setGoodsId(long goodsId){
    this.goodsId = goodsId;
}


public void setItemId(long itemId){
    this.itemId = itemId;
}


public void setGoodsPrice(BigDecimal goodsPrice){
    this.goodsPrice = goodsPrice;
}


public void setPayTime(Timestamp payTime){
    this.payTime = payTime;
}


@Basic
@Column(name = "version")
public byte getVersion(){
    return version;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


@Basic
@Column(name = "goods_num")
public int getGoodsNum(){
    return goodsNum;
}


@Basic
@Column(name = "order_type")
public byte getOrderType(){
    return orderType;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setVersion(byte version){
    this.version = version;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "goods_price")
public BigDecimal getGoodsPrice(){
    return goodsPrice;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Basic
@Column(name = "subtotal")
public BigDecimal getSubtotal(){
    return subtotal;
}


@Basic
@Column(name = "goods_type")
public String getGoodsType(){
    return goodsType;
}


@Basic
@Column(name = "real_price")
public BigDecimal getRealPrice(){
    return realPrice;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setRealPrice(BigDecimal realPrice){
    this.realPrice = realPrice;
}


@Basic
@Column(name = "user_id")
public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


@Basic
@Column(name = "goods_id")
public long getGoodsId(){
    return goodsId;
}


}