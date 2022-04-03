package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "countryCode" })
@XmlRootElement(name = "GetCountryByCountryCode")
public class GetCountryByCountryCode {

@XmlElement(name = "CountryCode")
 protected  String countryCode;


public void setCountryCode(String value){
    this.countryCode = value;
}


public String getCountryCode(){
    return countryCode;
}


}