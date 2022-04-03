package org.russianpost.custom_duty_info.data;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomDutyEvent")
public class CustomDutyEvent {

@XmlAttribute(name = "Number", required = true)
 protected  String number;

@XmlAttribute(name = "EventDateTime")
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar eventDateTime;

@XmlAttribute(name = "EventType", required = true)
 protected  BigInteger eventType;

@XmlAttribute(name = "EventName")
 protected  String eventName;

@XmlAttribute(name = "IndexTo")
 protected  String indexTo;

@XmlAttribute(name = "IndexEvent")
 protected  String indexEvent;

@XmlAttribute(name = "CountryEventCode")
 protected  String countryEventCode;

@XmlAttribute(name = "CountryToCode")
 protected  String countryToCode;

@XmlAttribute(name = "SumPaymentForward", required = true)
 protected  BigInteger sumPaymentForward;


public String getCountryEventCode(){
    return countryEventCode;
}


public String getIndexEvent(){
    return indexEvent;
}


public String getEventName(){
    return eventName;
}


public void setSumPaymentForward(BigInteger value){
    this.sumPaymentForward = value;
}


public void setIndexEvent(String value){
    this.indexEvent = value;
}


public XMLGregorianCalendar getEventDateTime(){
    return eventDateTime;
}


public void setNumber(String value){
    this.number = value;
}


public String getNumber(){
    return number;
}


public void setEventType(BigInteger value){
    this.eventType = value;
}


public void setCountryEventCode(String value){
    this.countryEventCode = value;
}


public BigInteger getEventType(){
    return eventType;
}


public String getIndexTo(){
    return indexTo;
}


public void setCountryToCode(String value){
    this.countryToCode = value;
}


public void setEventDateTime(XMLGregorianCalendar value){
    this.eventDateTime = value;
}


public void setIndexTo(String value){
    this.indexTo = value;
}


public String getCountryToCode(){
    return countryToCode;
}


public void setEventName(String value){
    this.eventName = value;
}


public BigInteger getSumPaymentForward(){
    return sumPaymentForward;
}


}