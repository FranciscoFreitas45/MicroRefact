package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getCountriesResult" })
@XmlRootElement(name = "GetCountriesResponse")
public class GetCountriesResponse {

@XmlElement(name = "GetCountriesResult")
 protected  String getCountriesResult;


public String getGetCountriesResult(){
    return getCountriesResult;
}


public void setGetCountriesResult(String value){
    this.getCountriesResult = value;
}


}