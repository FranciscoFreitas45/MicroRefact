package org.russianpost.rtm.dataexchangeespp.data;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {

 private  QName _PostalOrderEvent_QNAME;

 private  QName _PostalOrderEventsForMailInput_QNAME;

 private  QName _Password_QNAME;

 private  QName _Login_QNAME;

 private  QName _PostalOrderEventsForMailError_QNAME;

 private  QName _PostalOrderEventsForMailFault_QNAME;

 private  QName _PostalOrderEventsForMaiOutput_QNAME;


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "Password")
public JAXBElement<String> createPassword(String value){
    return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "PostalOrderEventsForMaiOutput")
public JAXBElement<PostalOrderEventsForMaiOutput> createPostalOrderEventsForMaiOutput(PostalOrderEventsForMaiOutput value){
    return new JAXBElement<PostalOrderEventsForMaiOutput>(_PostalOrderEventsForMaiOutput_QNAME, PostalOrderEventsForMaiOutput.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "PostalOrderEventsForMailFault")
public JAXBElement<PostalOrderEventsForMailFault> createPostalOrderEventsForMailFault(PostalOrderEventsForMailFault value){
    return new JAXBElement<PostalOrderEventsForMailFault>(_PostalOrderEventsForMailFault_QNAME, PostalOrderEventsForMailFault.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "PostalOrderEventsForMailError")
public JAXBElement<PostalOrderEventsForMailError> createPostalOrderEventsForMailError(PostalOrderEventsForMailError value){
    return new JAXBElement<PostalOrderEventsForMailError>(_PostalOrderEventsForMailError_QNAME, PostalOrderEventsForMailError.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "PostalOrderEvent")
public JAXBElement<PostalOrderEvent> createPostalOrderEvent(PostalOrderEvent value){
    return new JAXBElement<PostalOrderEvent>(_PostalOrderEvent_QNAME, PostalOrderEvent.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "Login")
public JAXBElement<String> createLogin(String value){
    return new JAXBElement<String>(_Login_QNAME, String.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/RTM/DataExchangeESPP/Data", name = "PostalOrderEventsForMailInput")
public JAXBElement<PostalOrderEventsForMailInput> createPostalOrderEventsForMailInput(PostalOrderEventsForMailInput value){
    return new JAXBElement<PostalOrderEventsForMailInput>(_PostalOrderEventsForMailInput_QNAME, PostalOrderEventsForMailInput.class, null, value);
}


}