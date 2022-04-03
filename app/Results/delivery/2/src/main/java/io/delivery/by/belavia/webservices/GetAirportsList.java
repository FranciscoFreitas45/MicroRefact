package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "language" })
@XmlRootElement(name = "GetAirportsList")
public class GetAirportsList {

@XmlElement(name = "Language")
 protected  String language;


public String getLanguage(){
    return language;
}


public void setLanguage(String value){
    this.language = value;
}


}