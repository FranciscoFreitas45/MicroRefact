package io.delivery.by.belavia.webservices;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFlight", propOrder = { "flight" })
public class ArrayOfFlight {

@XmlElement(name = "Flight", nillable = true)
 protected  List<Flight> flight;


public List<Flight> getFlight(){
    if (flight == null) {
        flight = new ArrayList<Flight>();
    }
    return this.flight;
}


}