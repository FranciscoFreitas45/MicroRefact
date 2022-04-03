package org.russianpost.custom_duty_info.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomDutyEventsForMailFault")
public class CustomDutyEventsForMailFault {

@XmlAttribute(name = "ErrorID")
 protected  CustomDutyEventsForMailErrors errorID;


public void setErrorID(CustomDutyEventsForMailErrors value){
    this.errorID = value;
}


public CustomDutyEventsForMailErrors getErrorID(){
    return errorID;
}


}