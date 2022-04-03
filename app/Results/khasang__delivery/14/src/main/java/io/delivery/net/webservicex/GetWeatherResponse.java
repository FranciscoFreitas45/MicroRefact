package io.delivery.net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getWeatherResult" })
@XmlRootElement(name = "GetWeatherResponse")
public class GetWeatherResponse {

@XmlElement(name = "GetWeatherResult")
 protected  String getWeatherResult;


public String getGetWeatherResult(){
    return getWeatherResult;
}


public void setGetWeatherResult(String value){
    this.getWeatherResult = value;
}


}