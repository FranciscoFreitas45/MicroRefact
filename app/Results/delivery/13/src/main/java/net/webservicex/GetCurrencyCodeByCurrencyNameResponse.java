package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCurrencyCodeByCurrencyNameResult" })
@XmlRootElement(name = "GetCurrencyCodeByCurrencyNameResponse")
public class GetCurrencyCodeByCurrencyNameResponse {

@XmlElement(name = "GetCurrencyCodeByCurrencyNameResult")
 protected  String getCurrencyCodeByCurrencyNameResult;


public void setGetCurrencyCodeByCurrencyNameResult(String value){
    this.getCurrencyCodeByCurrencyNameResult = value;
}


public String getGetCurrencyCodeByCurrencyNameResult(){
    return getCurrencyCodeByCurrencyNameResult;
}


}