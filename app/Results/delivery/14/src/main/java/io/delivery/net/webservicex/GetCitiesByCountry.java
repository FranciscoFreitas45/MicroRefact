package io.delivery.net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "countryName" })
@XmlRootElement(name = "GetCitiesByCountry")
public class GetCitiesByCountry {

@XmlElement(name = "CountryName")
 public  String countryName;


public void setCountryName(String value){
    this.countryName = value;
}


public String getCountryName(){
    return countryName;
}


}