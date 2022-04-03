package io.delivery.net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cityName", "countryName" })
@XmlRootElement(name = "GetWeather")
public class GetWeather {

@XmlElement(name = "CityName")
 protected  String cityName;

@XmlElement(name = "CountryName")
 protected  String countryName;


public String getCityName(){
    return cityName;
}


public void setCountryName(String value){
    this.countryName = value;
}


public String getCountryName(){
    return countryName;
}


public void setCityName(String value){
    this.cityName = value;
}


}