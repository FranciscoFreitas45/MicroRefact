package org.russianpost.custom_duty_info.data;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {

 private  QName _CustomDutyEvent_QNAME;

 private  QName _CustomDutyEventsForMailOutput_QNAME;

 private  QName _CustomDutyEventsForMailFault_QNAME;

 private  QName _CustomDutyEventsForMailInput_QNAME;

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.russianpost.custom_duty_info.data
 */
public ObjectFactory() {
}
@XmlElementDecl(namespace = "http://www.russianpost.org/custom-duty-info/data", name = "CustomDutyEventsForMailOutput")
public JAXBElement<CustomDutyEventsForMailOutput> createCustomDutyEventsForMailOutput(CustomDutyEventsForMailOutput value){
    return new JAXBElement<CustomDutyEventsForMailOutput>(_CustomDutyEventsForMailOutput_QNAME, CustomDutyEventsForMailOutput.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/custom-duty-info/data", name = "CustomDutyEventsForMailFault")
public JAXBElement<CustomDutyEventsForMailFault> createCustomDutyEventsForMailFault(CustomDutyEventsForMailFault value){
    return new JAXBElement<CustomDutyEventsForMailFault>(_CustomDutyEventsForMailFault_QNAME, CustomDutyEventsForMailFault.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/custom-duty-info/data", name = "CustomDutyEventsForMailInput")
public JAXBElement<CustomDutyEventsForMailInput> createCustomDutyEventsForMailInput(CustomDutyEventsForMailInput value){
    return new JAXBElement<CustomDutyEventsForMailInput>(_CustomDutyEventsForMailInput_QNAME, CustomDutyEventsForMailInput.class, null, value);
}


@XmlElementDecl(namespace = "http://www.russianpost.org/custom-duty-info/data", name = "CustomDutyEvent")
public JAXBElement<CustomDutyEvent> createCustomDutyEvent(CustomDutyEvent value){
    return new JAXBElement<CustomDutyEvent>(_CustomDutyEvent_QNAME, CustomDutyEvent.class, null, value);
}


}