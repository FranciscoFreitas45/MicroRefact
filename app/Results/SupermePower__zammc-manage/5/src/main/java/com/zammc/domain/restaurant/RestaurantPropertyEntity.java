package com.zammc.domain.restaurant;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "restaurant_property")
@DynamicUpdate
@DynamicInsert
public class RestaurantPropertyEntity {

 private  long id;

 private  long restaurantId;

 private  String restaurantProperty;

 private  String propertyImg;

 private  String propertyMsg;

 private  Integer sort;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  byte version;

 private  byte dataStatus;


@Basic
@Column(name = "version")
@Version
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


@Basic
@Column(name = "restaurant_id")
public long getRestaurantId(){
    return restaurantId;
}


public void setRestaurantProperty(String restaurantProperty){
    this.restaurantProperty = restaurantProperty;
}


@Id
@Column(name = "id")
public long getId(){
    return id;
}


public void setRestaurantId(long restaurantId){
    this.restaurantId = restaurantId;
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


public void setSort(Integer sort){
    this.sort = sort;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Basic
@Column(name = "sort")
public Integer getSort(){
    return sort;
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


@Basic
@Column(name = "restaurant_property")
public String getRestaurantProperty(){
    return restaurantProperty;
}


@Override
public String toString(){
    return "RestaurantPropertyEntity{" + "id=" + id + ", restaurantId=" + restaurantId + ", restaurantProperty='" + restaurantProperty + '\'' + ", propertyImg='" + propertyImg + '\'' + ", propertyMsg='" + propertyMsg + '\'' + ", sort=" + sort + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + '}';
}


}