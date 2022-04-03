package net.shangtech.weixin.property.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "sub_projects")
public class SubProject extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "project_name")
 private  String projectName;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "description")
 private  String description;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "name_en")
 private  String nameEn;

@Column(name = "image")
 private  String image;

@Column(name = "longitude")
 private  String longitude;

@Column(name = "latitude")
 private  String latitude;

@Column(name = "image_description")
 private  String imageDescription;

@Column(name = "peripheral")
 private  String peripheral;

@Column(name = "image_peripheral")
 private  String imagePeripheral;

@Column(name = "traffic")
 private  String traffic;

@Column(name = "IMAGE_TRAFFIC")
 private  String imageTraffic;

@Column(name = "price_avg")
 private  String priceAvg;

@Column(name = "project_type")
 private  Integer type;

@Column(name = "custom1")
 private  String custom1;


public String getProjectName(){
    return projectName;
}


public Date getCreateTime(){
    return createTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getPriceAvg(){
    return priceAvg;
}


public String getDescription(){
    return description;
}


public void setLatitude(String latitude){
    this.latitude = latitude;
}


public Integer getSort(){
    return sort;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public String getImageDescription(){
    return imageDescription;
}


public String getImagePeripheral(){
    return imagePeripheral;
}


public void setImagePeripheral(String imagePeripheral){
    this.imagePeripheral = imagePeripheral;
}


public void setTraffic(String traffic){
    this.traffic = traffic;
}


public String getNameEn(){
    return nameEn;
}


public void setLongitude(String longitude){
    this.longitude = longitude;
}


public void setNameEn(String nameEn){
    this.nameEn = nameEn;
}


public void setPriceAvg(String priceAvg){
    this.priceAvg = priceAvg;
}


public void setImageTraffic(String imageTraffic){
    this.imageTraffic = imageTraffic;
}


public String getCustom1(){
    return custom1;
}


public String getLongitude(){
    return longitude;
}


public String getImageTraffic(){
    return imageTraffic;
}


public String getLatitude(){
    return latitude;
}


public void setCustom1(String custom1){
    this.custom1 = custom1;
}


public void setImageDescription(String imageDescription){
    this.imageDescription = imageDescription;
}


public String getTraffic(){
    return traffic;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Integer getSysUserId(){
    return sysUserId;
}


public String getPeripheral(){
    return peripheral;
}


public void setType(Integer type){
    this.type = type;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public void setProjectName(String projectName){
    this.projectName = projectName;
}


public Integer getType(){
    return type;
}


public void setPeripheral(String peripheral){
    this.peripheral = peripheral;
}


public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}