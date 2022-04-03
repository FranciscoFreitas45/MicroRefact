package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "airport", "type", "viewDate" })
@XmlRootElement(name = "GetTimeTable")
public class GetTimeTable {

@XmlElement(name = "Airport")
 protected  String airport;

@XmlElement(name = "Type", required = true)
@XmlSchemaType(name = "string")
 protected  TimeTableType type;

@XmlElement(name = "ViewDate", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar viewDate;


public XMLGregorianCalendar getViewDate(){
    return viewDate;
}


public String getAirport(){
    return airport;
}


public TimeTableType getType(){
    return type;
}


public void setViewDate(XMLGregorianCalendar value){
    this.viewDate = value;
}


public void setAirport(String value){
    this.airport = value;
}


public void setType(TimeTableType value){
    this.type = value;
}


}