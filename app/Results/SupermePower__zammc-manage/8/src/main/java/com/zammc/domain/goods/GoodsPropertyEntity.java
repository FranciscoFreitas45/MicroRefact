package com.zammc.domain.goods;
 import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "goods_property")
public class GoodsPropertyEntity {

 private  long id;

 private  long goodsId;

 private  String propertyName;

 private  String propertyMsg;

 private  byte propertyStatus;

 private  String propertyImg;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  byte version;

 private  byte dataStatus;


@Basic
@Column(name = "version")
public byte getVersion(){
    return version;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setVersion(byte version){
    this.version = version;
}


@Id
@Column(name = "id")
public long getId(){
    return id;
}


public void setPropertyImg(String propertyImg){
    this.propertyImg = propertyImg;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


public void setPropertyMsg(String propertyMsg){
    this.propertyMsg = propertyMsg;
}


public void setPropertyStatus(byte propertyStatus){
    this.propertyStatus = propertyStatus;
}


@Basic
@Column(name = "property_status")
public byte getPropertyStatus(){
    return propertyStatus;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setPropertyName(String propertyName){
    this.propertyName = propertyName;
}


@Basic
@Column(name = "property_msg")
public String getPropertyMsg(){
    return propertyMsg;
}


public void setId(long id){
    this.id = id;
}


@Basic
@Column(name = "property_img")
public String getPropertyImg(){
    return propertyImg;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setGoodsId(long goodsId){
    this.goodsId = goodsId;
}


@Override
public String toString(){
    return "GoodsPropertyEntity{" + "id=" + id + ", goodsId=" + goodsId + ", propertyName='" + propertyName + '\'' + ", propertyMsg='" + propertyMsg + '\'' + ", propertyStatus=" + propertyStatus + ", propertyImg='" + propertyImg + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + '}';
}


@Basic
@Column(name = "property_name")
public String getPropertyName(){
    return propertyName;
}


@Basic
@Column(name = "goods_id")
public long getGoodsId(){
    return goodsId;
}


}