package com.cg.oms.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
@Entity
@Component
public class Address {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
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
public void setCity(String city){
    this.city = city;
}


public void setFlatNo(String flatNo){
    this.flatNo = flatNo;
}


public void setArea(String area){
    this.area = area;
}


public void setAddressId(Integer addressId){
    this.addressId = addressId;
}


public Integer getPinCode(){
    return pinCode;
}


public void setStreetName(String streetName){
    this.streetName = streetName;
}


public String getState(){
    return state;
}


public void setPinCode(Integer pinCode){
    this.pinCode = pinCode;
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


public void setState(String state){
    this.state = state;
}


@Override
public String toString(){
    return "Address [ " + flatNo + "\t" + streetName + "\t" + area + "\t" + city + "\t" + state + "\t" + pinCode + "\t" + "\t ]";
}


public String getArea(){
    return area;
}


public String getCity(){
    return city;
}


}