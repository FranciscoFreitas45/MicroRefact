package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "countryName" })
@XmlRootElement(name = "GetGMTbyCountry")
public class GetGMTbyCountry {

@XmlElement(name = "CountryName")
 protected  String countryName;


public void setCountryName(String value){
    this.countryName = value;
}


public String getCountryName(){
    return countryName;
}


}