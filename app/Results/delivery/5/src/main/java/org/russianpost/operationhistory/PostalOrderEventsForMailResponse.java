package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.rtm.dataexchangeespp.data.PostalOrderEventsForMaiOutput;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalOrderEventsForMailResponse", propOrder = { "postalOrderEventsForMaiOutput" })
public class PostalOrderEventsForMailResponse {

@XmlElement(name = "PostalOrderEventsForMaiOutput", namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data")
 protected  PostalOrderEventsForMaiOutput postalOrderEventsForMaiOutput;


public PostalOrderEventsForMaiOutput getPostalOrderEventsForMaiOutput(){
    return postalOrderEventsForMaiOutput;
}


public void setPostalOrderEventsForMaiOutput(PostalOrderEventsForMaiOutput value){
    this.postalOrderEventsForMaiOutput = value;
}


}