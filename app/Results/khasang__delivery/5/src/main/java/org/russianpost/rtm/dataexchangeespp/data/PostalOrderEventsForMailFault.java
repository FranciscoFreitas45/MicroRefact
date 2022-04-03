package org.russianpost.rtm.dataexchangeespp.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalOrderEventsForMailFault", propOrder = { "postalOrderEventsForMailError" })
public class PostalOrderEventsForMailFault {

@XmlElement(name = "PostalOrderEventsForMailError", required = true)
 protected  List<PostalOrderEventsForMailError> postalOrderEventsForMailError;


public List<PostalOrderEventsForMailError> getPostalOrderEventsForMailError(){
    if (postalOrderEventsForMailError == null) {
        postalOrderEventsForMailError = new ArrayList<PostalOrderEventsForMailError>();
    }
    return this.postalOrderEventsForMailError;
}


}