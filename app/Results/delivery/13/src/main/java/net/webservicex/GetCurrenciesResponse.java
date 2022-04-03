package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCurrenciesResult" })
@XmlRootElement(name = "GetCurrenciesResponse")
public class GetCurrenciesResponse {

@XmlElement(name = "GetCurrenciesResult")
 protected  String getCurrenciesResult;


public String getGetCurrenciesResult(){
    return getCurrenciesResult;
}


public void setGetCurrenciesResult(String value){
    this.getCurrenciesResult = value;
}


}