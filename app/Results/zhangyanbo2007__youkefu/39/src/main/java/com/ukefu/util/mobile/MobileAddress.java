package com.ukefu.util.mobile;
 import org.apache.commons.lang.StringUtils;
public class MobileAddress {

 private  String id;

 private  String code;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  String areacode;

 private  String zipcode;

public MobileAddress(String code, String areacode, String province, String city, String isp) {
    this.code = code;
    if (!StringUtils.isBlank(areacode)) {
        if (areacode.startsWith("0")) {
            this.areacode = areacode;
        } else {
            this.areacode = "0" + areacode;
        }
    }
    this.province = province;
    this.city = city;
    this.isp = isp;
}
public void setCountry(String country){
    this.country = country;
}


public String getAreacode(){
    return areacode;
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


public void setIsp(String isp){
    this.isp = isp;
}


public void setCode(String code){
    this.code = code;
}


public String getId(){
    return id;
}


public void setZipcode(String zipcode){
    this.zipcode = zipcode;
}


public String getZipcode(){
    return zipcode;
}


public void setAreacode(String areacode){
    this.areacode = areacode;
}


public String getProvince(){
    return province;
}


public String getIsp(){
    return isp;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


public String getCity(){
    return city;
}


}