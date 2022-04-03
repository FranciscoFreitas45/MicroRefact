package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCurrencyByCountryResult" })
@XmlRootElement(name = "GetCurrencyByCountryResponse")
public class GetCurrencyByCountryResponse {

@XmlElement(name = "GetCurrencyByCountryResult")
 protected  String getCurrencyByCountryResult;


public String getGetCurrencyByCountryResult(){
    return getCurrencyByCountryResult;
}


public void setGetCurrencyByCountryResult(String value){
    this.getCurrencyByCountryResult = value;
}


}