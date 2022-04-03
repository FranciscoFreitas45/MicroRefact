package com.zis.shop.bean;
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
@Table(name = "shop_item_mapping")
public class ShopItemMapping {

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "book_id")
 private  Integer bookId;

@Column(name = "shop_id")
 private  Integer shopId;

@Column(name = "p_item_id")
 private  Long pItemId;

@Column(name = "p_item_sku_id")
 private  Long pItemSkuId;

@Column(name = "title")
 private  String title;

@Column(name = "item_out_num")
 private  String itemOutNum;

@Column(name = "system_status")
 private  String systemStatus;

@Column(name = "upload_time")
 private  Date uploadTime;

@Column(name = "fail_reason")
 private  String failReason;

@Column(name = "create_time", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String name;

public ShopItemMapping() {
}public ShopItemMapping(Integer id, Integer bookId, Integer shopId, Long pItemId, Long pItemSkuId, String title, String itemOutNum, String systemStatus, Date uploadTime, String failReason, Date createTime, Date updateTime, Integer version) {
    super();
    this.id = id;
    this.bookId = bookId;
    this.shopId = shopId;
    this.pItemId = pItemId;
    this.pItemSkuId = pItemSkuId;
    this.title = title;
    this.itemOutNum = itemOutNum;
    this.systemStatus = systemStatus;
    this.uploadTime = uploadTime;
    this.failReason = failReason;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
}
public void setName(String name){
    this.name = name;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public Integer getId(){
    return id;
}


public void setpItemSkuId(Long pItemSkuId){
    this.pItemSkuId = pItemSkuId;
}


public String getTitle(){
    return title;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
}


public void setUploadTime(Date uploadTime){
    this.uploadTime = uploadTime;
}


public void setId(Integer id){
    this.id = id;
}


public String getSystemStatus(){
    return systemStatus;
}


public void setpItemId(Long pItemId){
    this.pItemId = pItemId;
}


public String getFailReason(){
    return failReason;
}


public Integer getVersion(){
    return version;
}


public Integer getShopId(){
    return shopId;
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getUploadTime(){
    return uploadTime;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setSystemStatus(String systemStatus){
    this.systemStatus = systemStatus;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getValue(){
    return value;
}


public String getItemOutNum(){
    return itemOutNum;
}


public void setValue(String value){
    this.value = value;
}


public Integer getBookId(){
    return bookId;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public Long getpItemId(){
    return pItemId;
}


public Long getpItemSkuId(){
    return pItemSkuId;
}


}