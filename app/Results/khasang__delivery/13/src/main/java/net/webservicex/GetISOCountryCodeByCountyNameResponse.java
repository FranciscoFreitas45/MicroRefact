package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getISOCountryCodeByCountyNameResult" })
@XmlRootElement(name = "GetISOCountryCodeByCountyNameResponse")
public class GetISOCountryCodeByCountyNameResponse {

@XmlElement(name = "GetISOCountryCodeByCountyNameResult")
 protected  String getISOCountryCodeByCountyNameResult;


public String getGetISOCountryCodeByCountyNameResult(){
    return getISOCountryCodeByCountyNameResult;
}


public void setGetISOCountryCodeByCountyNameResult(String value){
    this.getISOCountryCodeByCountyNameResult = value;
}


}