package com.gs.bean;
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


public void setCompanyOpendate(Date companyOpendate){
    this.companyOpendate = companyOpendate;
}


public void setCompanyDes(String companyDes){
    this.companyDes = companyDes;
}


public void setCompanyPricipal(String companyPricipal){
    this.companyPricipal = companyPricipal;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setCompanyWebsite(String companyWebsite){
    this.companyWebsite = companyWebsite;
}


public void setCompanyStatus(String companyStatus){
    this.companyStatus = companyStatus;
}


public void setCompanyLatitude(Double companyLatitude){
    this.companyLatitude = companyLatitude;
}


public String getCompanyLogo(){
    return companyLogo;
}


public void setCompanyPricipalphone(String companyPricipalphone){
    this.companyPricipalphone = companyPricipalphone;
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


public void setCompanyLogo(String companyLogo){
    this.companyLogo = companyLogo;
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


public void setCompanySize(String companySize){
    this.companySize = companySize;
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


public void setCompanyAddress(String companyAddress){
    this.companyAddress = companyAddress;
}


public String getCompanyPricipal(){
    return companyPricipal;
}


@Override
public String toString(){
    return "Company{" + "companyId='" + companyId + '\'' + ", companyName='" + companyName + '\'' + ", companyAddress='" + companyAddress + '\'' + ", companyTel='" + companyTel + '\'' + ", companyPricipal='" + companyPricipal + '\'' + ", companyWebsite='" + companyWebsite + '\'' + ", companyLogo='" + companyLogo + '\'' + ", companyOpendate=" + companyOpendate + ", companySize='" + companySize + '\'' + ", companyLongitude=" + companyLongitude + ", companyLatitude=" + companyLatitude + ", companyDes='" + companyDes + '\'' + ", companyStatus='" + companyStatus + '\'' + '}';
}


public Double getCompanyLongitude(){
    return companyLongitude;
}


public void setCompanyLongitude(Double companyLongitude){
    this.companyLongitude = companyLongitude;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompanyTel(String companyTel){
    this.companyTel = companyTel;
}


}