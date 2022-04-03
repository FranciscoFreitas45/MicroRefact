package com.gs.DTO;
 import java.util.Date;
public class Company {

 private  String companyId;

 private  String companyName;

 private  String companyAddress;

 private  String companyTel;

 private  String companyPricipal;

 private  String companyPricipalphone;

 private  String companyWebsite;

 private  String companyLogo;

 private  Date companyOpendate;

 private  String companySize;

 private  Double companyLongitude;

 private  Double companyLatitude;

 private  String companyDes;

 private  String companyStatus;


public void setCompanyDes(String companyDes){
    this.companyDes = companyDes;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setCompanyStatus(String companyStatus){
    this.companyStatus = companyStatus;
}


public String getCompanyLogo(){
    return companyLogo;
}


public Date getCompanyOpendate(){
    return companyOpendate;
}


public String getCompanyStatus(){
    return companyStatus;
}


public String getCompanySize(){
    return companySize;
}


public String getCompanyTel(){
    return companyTel;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public Double getCompanyLatitude(){
    return companyLatitude;
}


public String getCompanyPricipalphone(){
    return companyPricipalphone;
}


public String getCompanyDes(){
    return companyDes;
}


public String getCompanyId(){
    return companyId;
}


public String getCompanyAddress(){
    return companyAddress;
}


public String getCompanyName(){
    return companyName;
}


public String getCompanyPricipal(){
    return companyPricipal;
}


public Double getCompanyLongitude(){
    return companyLongitude;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


}