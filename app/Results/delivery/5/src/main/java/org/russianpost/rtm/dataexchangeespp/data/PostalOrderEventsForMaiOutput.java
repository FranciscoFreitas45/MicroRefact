package org.russianpost.rtm.dataexchangeespp.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalOrderEventsForMaiOutput", propOrder = { "postalOrderEvent" })
public class PostalOrderEventsForMaiOutput {

@XmlElement(name = "PostalOrderEvent")
 protected  List<PostalOrderEvent> postalOrderEvent;


public List<PostalOrderEvent> getPostalOrderEvent(){
    if (postalOrderEvent == null) {
        postalOrderEvent = new ArrayList<PostalOrderEvent>();
    }
    return this.postalOrderEvent;
}


}