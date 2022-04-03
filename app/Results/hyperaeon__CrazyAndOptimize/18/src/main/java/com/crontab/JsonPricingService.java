package com.crontab;
 public class JsonPricingService {

 private  String pricingServiceId;

 private  String shortName;

 private  String fullName;


public void setPricingServiceId(String pricingServiceId){
    this.pricingServiceId = pricingServiceId;
}


public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public String getFullName(){
    return fullName;
}


public String getPricingServiceId(){
    return pricingServiceId;
}


}