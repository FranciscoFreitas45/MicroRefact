package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCountryByCountryCodeResult" })
@XmlRootElement(name = "GetCountryByCountryCodeResponse")
public class GetCountryByCountryCodeResponse {

@XmlElement(name = "GetCountryByCountryCodeResult")
 protected  String getCountryByCountryCodeResult;


public String getGetCountryByCountryCodeResult(){
    return getCountryByCountryCodeResult;
}


public void setGetCountryByCountryCodeResult(String value){
    this.getCountryByCountryCodeResult = value;
}


}