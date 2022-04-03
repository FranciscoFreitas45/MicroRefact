package com.zammc.domain.restaurant;
 import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

 private  long restaurantId;

 private  String restaurantName;

 private  String restaurantImg;

 private  String restaurantCode;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  int version;

 private  byte dataStatus;

 private  byte status;

 private  String summary;


@Basic
@Column(name = "version")
@Version
public int getVersion(){
    return version;
}


public void setRestaurantName(String restaurantName){
    this.restaurantName = restaurantName;
}


public void setSummary(String summary){
    this.summary = summary;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "summary")
public String getSummary(){
    return summary;
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


@Id
@Column(name = "restaurant_id")
public long getRestaurantId(){
    return restaurantId;
}


public void setRestaurantId(long restaurantId){
    this.restaurantId = restaurantId;
}


@Basic
@Column(name = "restaurant_img")
public String getRestaurantImg(){
    return restaurantImg;
}


public void setRestaurantCode(String restaurantCode){
    this.restaurantCode = restaurantCode;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "restaurant_name")
public String getRestaurantName(){
    return restaurantName;
}


@Basic
@Column(name = "status")
public byte getStatus(){
    return status;
}


public void setStatus(byte status){
    this.status = status;
}


@Basic
@Column(name = "restaurant_code")
public String getRestaurantCode(){
    return restaurantCode;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setRestaurantImg(String restaurantImg){
    this.restaurantImg = restaurantImg;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "RestaurantEntity{" + "restaurantId=" + restaurantId + ", restaurantName='" + restaurantName + '\'' + ", restaurantImg='" + restaurantImg + '\'' + ", restaurantCode='" + restaurantCode + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + ", status=" + status + ", summary='" + summary + '\'' + '}';
}


}