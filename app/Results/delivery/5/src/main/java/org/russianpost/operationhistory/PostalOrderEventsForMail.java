package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.AuthorizationHeader;
import org.russianpost.rtm.dataexchangeespp.data.PostalOrderEventsForMailInput;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalOrderEventsForMail", propOrder = { "authorizationHeader", "postalOrderEventsForMailInput" })
public class PostalOrderEventsForMail {

@XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
 protected  AuthorizationHeader authorizationHeader;

@XmlElement(name = "PostalOrderEventsForMailInput", namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data")
 protected  PostalOrderEventsForMailInput postalOrderEventsForMailInput;


public void setPostalOrderEventsForMailInput(PostalOrderEventsForMailInput value){
    this.postalOrderEventsForMailInput = value;
}


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


public PostalOrderEventsForMailInput getPostalOrderEventsForMailInput(){
    return postalOrderEventsForMailInput;
}


}