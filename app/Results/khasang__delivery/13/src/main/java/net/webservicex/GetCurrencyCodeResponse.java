package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCurrencyCodeResult" })
@XmlRootElement(name = "GetCurrencyCodeResponse")
public class GetCurrencyCodeResponse {

@XmlElement(name = "GetCurrencyCodeResult")
 protected  String getCurrencyCodeResult;


public String getGetCurrencyCodeResult(){
    return getCurrencyCodeResult;
}


public void setGetCurrencyCodeResult(String value){
    this.getCurrencyCodeResult = value;
}


}