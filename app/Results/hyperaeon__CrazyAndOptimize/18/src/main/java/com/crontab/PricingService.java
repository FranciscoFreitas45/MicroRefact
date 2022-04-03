package com.crontab;
 import java.util.Date;
public class PricingService {

 private  String pricingServiceId;

 private  String fullName;

 private  String omsProdCateId;

 private  String shortName;

 private  String updatedBy;

 private  Date updatedDatetime;

public PricingService() {
}
public void setOmsProdCateId(String omsProdCateId){
    this.omsProdCateId = omsProdCateId;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public void setUpdatedDatetime(Date updatedDatetime){
    this.updatedDatetime = (Date) updatedDatetime.clone();
}


public void setPricingServiceId(String pricingServiceId){
    this.pricingServiceId = pricingServiceId;
}


public String getShortName(){
    return this.shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public Date getUpdatedDatetime(){
    return (Date) this.updatedDatetime.clone();
}


public String getFullName(){
    return this.fullName;
}


public String getPricingServiceId(){
    return this.pricingServiceId;
}


public String getOmsProdCateId(){
    return this.omsProdCateId;
}


}