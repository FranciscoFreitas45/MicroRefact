package com.ukefu.util;
 public class IP {

 private  long serialVersionUID;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  String region;


public void setCountry(String country){
    this.country = country;
}


public void setRegion(String region){
    this.region = region;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public void setCity(String city){
    this.city = city;
}


public String getRegion(){
    return region;
}


public void setIsp(String isp){
    this.isp = isp;
}


public String getProvince(){
    return province;
}


public String getIsp(){
    return isp;
}


public String toString(){
    return "0".equals(this.province) || "0".equals(this.city) ? this.country : this.province != null || this.city != null ? (this.province != null ? this.province : "" + this.city != null ? this.city : "") : this.getRegion() != null ? this.getRegion() : "未知";
}


public String getCity(){
    return city;
}


}