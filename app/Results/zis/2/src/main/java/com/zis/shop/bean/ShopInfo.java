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
@Table(name = "shop_info")
public class ShopInfo {

@Id
@GeneratedValue
@Column(name = "shop_id")
 private  Integer shopId;

@Column(name = "company_id")
 private  Integer companyId;

@Column(name = "shop_name")
 private  String shopName;

@Column(name = "shop_url")
 private  String shopUrl;

@Column(name = "app_id")
 private  String appId;

@Column(name = "app_secret")
 private  String appSecret;

@Column(name = "discount")
 private  Double discount;

@Column(name = "delivery_template_id")
 private  Long deliveryTemplateId;

@Column(name = "template_id")
 private  Long templateId;

@Column(name = "p_name")
 private  String pName;

@Column(name = "emails")
 private  String emails;

@Column(name = "status")
 private  String status;

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

public ShopInfo() {
}public ShopInfo(Integer shopId, Integer companyId, String shopName, String shopUrl, String appId, String appSecret, Double discount, Long deliveryTemplateId, Long templateId, String pName, String emails, String status, Date createTime, Date updateTime, Integer version) {
    super();
    this.shopId = shopId;
    this.companyId = companyId;
    this.shopName = shopName;
    this.shopUrl = shopUrl;
    this.appId = appId;
    this.appSecret = appSecret;
    this.discount = discount;
    this.deliveryTemplateId = deliveryTemplateId;
    this.templateId = templateId;
    this.pName = pName;
    this.emails = emails;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
}
public void setName(String name){
    this.name = name;
}


public String getAppSecret(){
    return appSecret;
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


public void setpName(String pName){
    this.pName = pName;
}


public void setEmails(String emails){
    this.emails = emails;
}


public String getStatus(){
    return status;
}


public String getAppId(){
    return appId;
}


public Long getTemplateId(){
    return templateId;
}


public String getEmails(){
    return emails;
}


public String getShopName(){
    return shopName;
}


public Double getDiscount(){
    return discount;
}


public void setDiscount(Double discount){
    this.discount = discount;
}


public void setShopName(String shopName){
    this.shopName = shopName;
}


public Integer getVersion(){
    return version;
}


public String getShopUrl(){
    return shopUrl;
}


public void setAppSecret(String appSecret){
    this.appSecret = appSecret;
}


public void setShopUrl(String shopUrl){
    this.shopUrl = shopUrl;
}


public Integer getShopId(){
    return shopId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setTemplateId(Long templateId){
    this.templateId = templateId;
}


public String getpName(){
    return pName;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setAppId(String appId){
    this.appId = appId;
}


public Long getDeliveryTemplateId(){
    return deliveryTemplateId;
}


public void setStatus(String status){
    this.status = status;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getValue(){
    return value;
}


public Integer getCompanyId(){
    return companyId;
}


public void setDeliveryTemplateId(Long deliveryTemplateId){
    this.deliveryTemplateId = deliveryTemplateId;
}


public void setValue(String value){
    this.value = value;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "ShopInfo [shopId=" + shopId + ", companyId=" + companyId + ", shopName=" + shopName + ", shopUrl=" + shopUrl + ", appId=" + appId + ", appSecret=" + appSecret + ", discount=" + discount + ", deliveryTemplateId=" + deliveryTemplateId + ", templateId=" + templateId + ", pName=" + pName + ", emails=" + emails + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


}