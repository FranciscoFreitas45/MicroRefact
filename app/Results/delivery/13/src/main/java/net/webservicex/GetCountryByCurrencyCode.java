package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "currencyCode" })
@XmlRootElement(name = "GetCountryByCurrencyCode")
public class GetCountryByCurrencyCode {

@XmlElement(name = "CurrencyCode")
 protected  String currencyCode;


public String getCurrencyCode(){
    return currencyCode;
}


public void setCurrencyCode(String value){
    this.currencyCode = value;
}


}