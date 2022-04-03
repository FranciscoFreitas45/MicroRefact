package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", namespace = "http://www.opengis.net/context", propOrder = { "addressType", "address", "city", "stateOrProvince", "postCode", "country" })
public class AddressType {

@XmlElement(name = "AddressType")
 protected  String addressType;

@XmlElement(name = "Address")
 protected  String address;

@XmlElement(name = "City")
 protected  String city;

@XmlElement(name = "StateOrProvince")
 protected  String stateOrProvince;

@XmlElement(name = "PostCode")
 protected  String postCode;

@XmlElement(name = "Country")
 protected  String country;


public void setCountry(String value){
    this.country = value;
}


public String getPostCode(){
    return postCode;
}


public void setAddress(String value){
    this.address = value;
}


public String getCountry(){
    return country;
}


public void setCity(String value){
    this.city = value;
}


public void setPostCode(String value){
    this.postCode = value;
}


public String getAddressType(){
    return addressType;
}


public void setAddressType(String value){
    this.addressType = value;
}


public String getAddress(){
    return address;
}


public void setStateOrProvince(String value){
    this.stateOrProvince = value;
}


public String getStateOrProvince(){
    return stateOrProvince;
}


public String getCity(){
    return city;
}


}