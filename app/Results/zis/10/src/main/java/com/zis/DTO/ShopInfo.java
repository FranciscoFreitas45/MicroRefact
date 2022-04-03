package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class ShopInfo {

 private  Integer shopId;

 private  Integer companyId;

 private  String shopName;

 private  String shopUrl;

 private  String appId;

 private  String appSecret;

 private  Double discount;

 private  Long deliveryTemplateId;

 private  Long templateId;

 private  String pName;

 private  String emails;

 private  String status;

 private  Date createTime;

 private  Date updateTime;

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
public String getAppSecret(){
    return appSecret;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setpName(String pName){
    this.pName = pName;
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


public void setShopName(String shopName){
    this.shopName = shopName;
}


public Integer getVersion(){
    return version;
}


public String getShopUrl(){
    return shopUrl;
}


public void setShopUrl(String shopUrl){
    this.shopUrl = shopUrl;
}


public Integer getShopId(){
    return shopId;
}


public void setTemplateId(Long templateId){
    this.templateId = templateId;
}


public String getpName(){
    return pName;
}


public void setAppId(String appId){
    this.appId = appId;
}


public Long getDeliveryTemplateId(){
    return deliveryTemplateId;
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


public void setValue(String value){
    this.value = value;
}


@Override
public String toString(){
    return "ShopInfo [shopId=" + shopId + ", companyId=" + companyId + ", shopName=" + shopName + ", shopUrl=" + shopUrl + ", appId=" + appId + ", appSecret=" + appSecret + ", discount=" + discount + ", deliveryTemplateId=" + deliveryTemplateId + ", templateId=" + templateId + ", pName=" + pName + ", emails=" + emails + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + "]";
}


}