package com.zammc.domain.coupon;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "coupon")
@DynamicUpdate
@DynamicInsert
public class CouponEntity {

 private  long id;

 private  int couponNum;

 private  byte couponType;

 private  String couponImg;

 private  BigDecimal couponSize;

 private  BigDecimal couponCondition;

 private  String couponMsg;

 private  Timestamp couponTime;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  byte version;

 private  byte dataStatus;

 private  String couponName;


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


@Id
@Column(name = "id")
public long getId(){
    return id;
}


@Basic
@Column(name = "coupon_condition")
public BigDecimal getCouponCondition(){
    return couponCondition;
}


@Basic
@Column(name = "coupon_name")
public String getCouponName(){
    return couponName;
}


public void setId(long id){
    this.id = id;
}


@Basic
@Column(name = "coupon_time")
public Timestamp getCouponTime(){
    return couponTime;
}


@Basic
@Column(name = "version")
@Version
public byte getVersion(){
    return version;
}


@Basic
@Column(name = "coupon_type")
public byte getCouponType(){
    return couponType;
}


@Basic
@Column(name = "coupon_num")
public int getCouponNum(){
    return couponNum;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setVersion(byte version){
    this.version = version;
}


@Basic
@Column(name = "coupon_img")
public String getCouponImg(){
    return couponImg;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "coupon_size")
public BigDecimal getCouponSize(){
    return couponSize;
}


@Basic
@Column(name = "coupon_msg")
public String getCouponMsg(){
    return couponMsg;
}


public void setCouponName(String couponName){
    this.couponName = couponName;
}


public void setCouponTime(Timestamp couponTime){
    this.couponTime = couponTime;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setCouponSize(BigDecimal couponSize){
    this.couponSize = couponSize;
}


public void setCouponMsg(String couponMsg){
    this.couponMsg = couponMsg;
}


public void setCouponType(byte couponType){
    this.couponType = couponType;
}


public void setCouponCondition(BigDecimal couponCondition){
    this.couponCondition = couponCondition;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setCouponImg(String couponImg){
    this.couponImg = couponImg;
}


@Override
public String toString(){
    return "CouponEntity{" + "id=" + id + ", couponNum=" + couponNum + ", couponType=" + couponType + ", couponImg='" + couponImg + '\'' + ", couponSize=" + couponSize + ", couponCondition=" + couponCondition + ", couponMsg='" + couponMsg + '\'' + ", couponTime=" + couponTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + ", couponName='" + couponName + '\'' + '}';
}


public void setCouponNum(int couponNum){
    this.couponNum = couponNum;
}


}