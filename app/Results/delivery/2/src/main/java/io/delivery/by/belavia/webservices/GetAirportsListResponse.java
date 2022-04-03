package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getAirportsListResult" })
@XmlRootElement(name = "GetAirportsListResponse")
public class GetAirportsListResponse {

@XmlElement(name = "GetAirportsListResult")
 protected  AirportsResponse getAirportsListResult;


public AirportsResponse getGetAirportsListResult(){
    return getAirportsListResult;
}


public void setGetAirportsListResult(AirportsResponse value){
    this.getAirportsListResult = value;
}


}