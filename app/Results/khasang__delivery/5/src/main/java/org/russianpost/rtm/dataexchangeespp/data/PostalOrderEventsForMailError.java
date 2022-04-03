package org.russianpost.rtm.dataexchangeespp.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalOrderEventsForMailError")
public class PostalOrderEventsForMailError {

@XmlAttribute(name = "ErrorID")
 protected  PostalOrderEventsForMailErrors errorID;


public void setErrorID(PostalOrderEventsForMailErrors value){
    this.errorID = value;
}


public PostalOrderEventsForMailErrors getErrorID(){
    return errorID;
}


}