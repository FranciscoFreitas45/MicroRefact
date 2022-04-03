package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getGMTbyCountryResult" })
@XmlRootElement(name = "GetGMTbyCountryResponse")
public class GetGMTbyCountryResponse {

@XmlElement(name = "GetGMTbyCountryResult")
 protected  String getGMTbyCountryResult;


public void setGetGMTbyCountryResult(String value){
    this.getGMTbyCountryResult = value;
}


public String getGetGMTbyCountryResult(){
    return getGMTbyCountryResult;
}


}