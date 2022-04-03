package com.yalcin.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
public class Adress {

 private  Integer id;

 private  User user;

 private  String country;

 private  String province;

 private  String district;

 private  String street;

 private  String buildingNumber;

 private  String adressType;

 private Integer id;

public Adress() {
}public Adress(String country, String province, String district, String street, String buildingNumber, String adressType) {
    this.country = country;
    this.province = province;
    this.district = district;
    this.street = street;
    this.buildingNumber = buildingNumber;
    this.adressType = adressType;
}
public String getAdressType(){
    return adressType;
}


public String getCountry(){
    return country;
}


public String getbuildingNumber(){
    return buildingNumber;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public Integer getId(){
    return id;
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


}