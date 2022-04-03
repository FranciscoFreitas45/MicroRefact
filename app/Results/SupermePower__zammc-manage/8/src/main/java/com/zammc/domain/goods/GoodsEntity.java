package com.zammc.domain.goods;
 import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "goods")
public class GoodsEntity {

 private  long goodsId;

 private  String goodsName;

 private  String goodsImg;

 private  long goodsCate;

 private  String goodsType;

 private  BigDecimal goodsPrice;

 private  BigDecimal goodsDiscount;

 private  byte goodsStatus;

 private  String goodsMsg;

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
@Column(name = "goods_status")
public byte getGoodsStatus(){
    return goodsStatus;
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
@Column(name = "goods_cate")
public long getGoodsCate(){
    return goodsCate;
}


public void setGoodsType(String goodsType){
    this.goodsType = goodsType;
}


public void setGoodsImg(String goodsImg){
    this.goodsImg = goodsImg;
}


public void setGoodsMsg(String goodsMsg){
    this.goodsMsg = goodsMsg;
}


@Basic
@Column(name = "goods_discount")
public BigDecimal getGoodsDiscount(){
    return goodsDiscount;
}


public void setGoodsId(long goodsId){
    this.goodsId = goodsId;
}


public void setGoodsCate(long goodsCate){
    this.goodsCate = goodsCate;
}


public void setGoodsDiscount(BigDecimal goodsDiscount){
    this.goodsDiscount = goodsDiscount;
}


public void setGoodsPrice(BigDecimal goodsPrice){
    this.goodsPrice = goodsPrice;
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
@Column(name = "goods_img")
public String getGoodsImg(){
    return goodsImg;
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
@Column(name = "goods_msg")
public String getGoodsMsg(){
    return goodsMsg;
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
@Column(name = "goods_type")
public String getGoodsType(){
    return goodsType;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "GoodsEntity{" + "goodsId=" + goodsId + ", goodsName='" + goodsName + '\'' + ", goodsImg='" + goodsImg + '\'' + ", goodsCate=" + goodsCate + ", goodsType='" + goodsType + '\'' + ", goodsPrice=" + goodsPrice + ", goodsDiscount=" + goodsDiscount + ", goodsStatus=" + goodsStatus + ", goodsMsg='" + goodsMsg + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + '}';
}


public void setGoodsStatus(byte goodsStatus){
    this.goodsStatus = goodsStatus;
}


@Id
@Column(name = "goods_id")
public long getGoodsId(){
    return goodsId;
}


}