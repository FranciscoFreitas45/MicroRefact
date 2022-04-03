package com.cg.oms.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
public class Address {

 private  Integer addressId;

 private  String flatNo;

 private  String streetName;

 private  String area;

 private  String city;

 private  String state;

 private  Integer pinCode;

/**
 * creating default constructors
 */
public Address() {
    super();
}
public Integer getPinCode(){
    return pinCode;
}


public String getState(){
    return state;
}


public Integer getAddressId(){
    return addressId;
}


public String getFlatNo(){
    return flatNo;
}


public String getStreetName(){
    return streetName;
}


public String getArea(){
    return area;
}


public String getCity(){
    return city;
}

public void setAddressId(Integer addressId){
    this.addressId = addressId;
}

}