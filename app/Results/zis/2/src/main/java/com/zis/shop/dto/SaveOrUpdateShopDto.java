package com.zis.shop.dto;
 import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
public class SaveOrUpdateShopDto {

 private  Integer shopId;

 private  Integer companyId;

@NotBlank(message = "店铺名称不能为空")
 private  String shopName;

 private  String shopUrl;

 private  String appId;

 private  String appSecret;

@NotBlank(message = "平台名称不能为空")
 private  String pName;

@Email(message = "邮箱格式不符")
@NotBlank(message = "邮箱不能为空")
 private  String emails;

@NotBlank(message = "折扣率不能为空")
@Pattern(regexp = "^(([1]{1})|([0]{1}\\.[0-9]{1,2}))$", message = "折扣率请填1至0.01之间的数字")
 private  String discount;

@NotNull(message = "运费模板不能为空")
 private  Long deliveryTemplateId;

 private  Long templateId;

 private  String typeStatus;


public String getAppSecret(){
    return appSecret;
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


public String getDiscount(){
    return discount;
}


public void setDiscount(String discount){
    this.discount = discount;
}


public void setShopName(String shopName){
    this.shopName = shopName;
}


public void setTypeStatus(String typeStatus){
    this.typeStatus = typeStatus;
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


public void setTemplateId(Long templateId){
    this.templateId = templateId;
}


public String getpName(){
    return pName;
}


public void setAppId(String appId){
    this.appId = appId;
}


public String getTypeStatus(){
    return typeStatus;
}


public Long getDeliveryTemplateId(){
    return deliveryTemplateId;
}


public Integer getCompanyId(){
    return companyId;
}


public void setDeliveryTemplateId(Long deliveryTemplateId){
    this.deliveryTemplateId = deliveryTemplateId;
}


@Override
public String toString(){
    return "SaveOrUpdateShopDto [shopId=" + shopId + ", companyId=" + companyId + ", shopName=" + shopName + ", shopUrl=" + shopUrl + ", appId=" + appId + ", appSecret=" + appSecret + ", pName=" + pName + ", emails=" + emails + ", discount=" + discount + ", deliveryTemplateId=" + deliveryTemplateId + ", templateId=" + templateId + ", typeStatus=" + typeStatus + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


}