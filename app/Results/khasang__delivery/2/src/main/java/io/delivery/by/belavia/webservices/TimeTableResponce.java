package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTableResponce", propOrder = { "flights" })
public class TimeTableResponce {

@XmlElement(name = "Flights")
 protected  ArrayOfFlight flights;


public ArrayOfFlight getFlights(){
    return flights;
}


public void setFlights(ArrayOfFlight value){
    this.flights = value;
}


}