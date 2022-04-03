package com.yalcin.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
public class Adress {

 private  Integer id;

 private  User user;

 private  String country;

 private  String province;

 private  String district;

 private  String street;

 private  String buildingNumber;

 private  String adressType;

public Adress() {
}public Adress(String country, String province, String district, String street, String buildingNumber, String adressType) {
    this.country = country;
    this.province = province;
    this.district = district;
    this.street = street;
    this.buildingNumber = buildingNumber;
    this.adressType = adressType;
}
public void setCountry(String country){
    this.country = country;
}


public String getAdressType(){
    return adressType;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public void setStreet(String street){
    this.street = street;
}


public String getbuildingNumber(){
    return buildingNumber;
}


public User getUser(){
    return user;
}


public Integer getId(){
    return id;
}


public void setbuildingNumber(String buildingNumber){
    this.buildingNumber = buildingNumber;
}


public String getStreet(){
    return street;
}


public String getDistrict(){
    return district;
}


public String getProvince(){
    return province;
}


public void setId(Integer id){
    this.id = id;
}


public void setDistrict(String district){
    this.district = district;
}


public void setAdressType(String adressType){
    this.adressType = adressType;
}


public void setUser(User user){
    this.user = user;
}


}