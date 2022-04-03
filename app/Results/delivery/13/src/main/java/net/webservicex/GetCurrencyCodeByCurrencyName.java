package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "currencyName" })
@XmlRootElement(name = "GetCurrencyCodeByCurrencyName")
public class GetCurrencyCodeByCurrencyName {

@XmlElement(name = "CurrencyName")
 protected  String currencyName;


public String getCurrencyName(){
    return currencyName;
}


public void setCurrencyName(String value){
    this.currencyName = value;
}


}