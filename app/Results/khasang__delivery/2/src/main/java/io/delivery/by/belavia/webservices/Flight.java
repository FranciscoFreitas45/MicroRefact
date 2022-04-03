package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flight")
public class Flight {

@XmlAttribute(name = "FlightNumber")
 protected  String flightNumber;

@XmlAttribute(name = "Airport")
 protected  String airport;

@XmlAttribute(name = "ScheduleTime", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar scheduleTime;

@XmlAttribute(name = "ExpectedTime", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar expectedTime;

@XmlAttribute(name = "ActualTime", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar actualTime;

@XmlAttribute(name = "Status")
 protected  String status;

@XmlAttribute(name = "Aircraft")
 protected  String aircraft;


public String getAirport(){
    return airport;
}


public void setScheduleTime(XMLGregorianCalendar value){
    this.scheduleTime = value;
}


public String getFlightNumber(){
    return flightNumber;
}


public String getAircraft(){
    return aircraft;
}


public void setFlightNumber(String value){
    this.flightNumber = value;
}


public void setAirport(String value){
    this.airport = value;
}


public String getStatus(){
    return status;
}


public XMLGregorianCalendar getScheduleTime(){
    return scheduleTime;
}


public void setActualTime(XMLGregorianCalendar value){
    this.actualTime = value;
}


public void setStatus(String value){
    this.status = value;
}


public void setExpectedTime(XMLGregorianCalendar value){
    this.expectedTime = value;
}


public void setAircraft(String value){
    this.aircraft = value;
}


public XMLGregorianCalendar getExpectedTime(){
    return expectedTime;
}


public XMLGregorianCalendar getActualTime(){
    return actualTime;
}


}