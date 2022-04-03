package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCountryByCurrencyCodeResult" })
@XmlRootElement(name = "GetCountryByCurrencyCodeResponse")
public class GetCountryByCurrencyCodeResponse {

@XmlElement(name = "GetCountryByCurrencyCodeResult")
 protected  String getCountryByCurrencyCodeResult;


public String getGetCountryByCurrencyCodeResult(){
    return getCountryByCurrencyCodeResult;
}


public void setGetCountryByCurrencyCodeResult(String value){
    this.getCountryByCurrencyCodeResult = value;
}


}