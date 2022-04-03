package io.delivery.by.belavia.webservices;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AirportsResponse", propOrder = { "airport" })
public class AirportsResponse {

@XmlElement(name = "Airport")
 protected  List<Airport> airport;


public List<Airport> getAirport(){
    if (airport == null) {
        airport = new ArrayList<Airport>();
    }
    return this.airport;
}


}