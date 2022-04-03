package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationParameters", propOrder = { "operType", "operAttr", "operDate" })
public class OperationParameters {

@XmlElement(name = "OperType", required = true)
 protected  Rtm02Parameter operType;

@XmlElement(name = "OperAttr", required = true)
 protected  Rtm02Parameter operAttr;

@XmlElement(name = "OperDate", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar operDate;


public Rtm02Parameter getOperType(){
    return operType;
}


public Rtm02Parameter getOperAttr(){
    return operAttr;
}


public void setOperDate(XMLGregorianCalendar value){
    this.operDate = value;
}


public XMLGregorianCalendar getOperDate(){
    return operDate;
}


public void setOperType(Rtm02Parameter value){
    this.operType = value;
}


public void setOperAttr(Rtm02Parameter value){
    this.operAttr = value;
}


}