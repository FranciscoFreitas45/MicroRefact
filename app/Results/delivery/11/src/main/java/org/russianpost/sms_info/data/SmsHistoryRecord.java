package org.russianpost.sms_info.data;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmsHistoryRecord", propOrder = { "id", "number", "notificationTypeID", "notificationTypeName", "notificationStatusID", "notificationStatusName", "notificationStatusDate", "sendedID", "sourceOperation" })
public class SmsHistoryRecord {

@XmlElement(name = "Id", required = true)
 protected  BigInteger id;

@XmlElement(name = "Number", required = true)
 protected  String number;

@XmlElement(name = "NotificationTypeID", required = true)
 protected  BigInteger notificationTypeID;

@XmlElement(name = "NotificationTypeName", required = true)
 protected  String notificationTypeName;

@XmlElement(name = "NotificationStatusID", required = true)
 protected  BigInteger notificationStatusID;

@XmlElement(name = "NotificationStatusName", required = true)
 protected  String notificationStatusName;

@XmlElement(name = "NotificationStatusDate", required = true)
@XmlSchemaType(name = "dateTime")
 protected  XMLGregorianCalendar notificationStatusDate;

@XmlElement(name = "SendedID", required = true)
 protected  BigInteger sendedID;

@XmlElement(name = "SourceOperation", required = true)
 protected  String sourceOperation;


public void setNotificationStatusName(String value){
    this.notificationStatusName = value;
}


public XMLGregorianCalendar getNotificationStatusDate(){
    return notificationStatusDate;
}


public void setSourceOperation(String value){
    this.sourceOperation = value;
}


public BigInteger getSendedID(){
    return sendedID;
}


public String getSourceOperation(){
    return sourceOperation;
}


public void setNotificationStatusDate(XMLGregorianCalendar value){
    this.notificationStatusDate = value;
}


public BigInteger getId(){
    return id;
}


public BigInteger getNotificationStatusID(){
    return notificationStatusID;
}


public BigInteger getNotificationTypeID(){
    return notificationTypeID;
}


public void setNotificationTypeID(BigInteger value){
    this.notificationTypeID = value;
}


public void setNotificationStatusID(BigInteger value){
    this.notificationStatusID = value;
}


public void setNumber(String value){
    this.number = value;
}


public String getNumber(){
    return number;
}


public void setSendedID(BigInteger value){
    this.sendedID = value;
}


public String getNotificationTypeName(){
    return notificationTypeName;
}


public String getNotificationStatusName(){
    return notificationStatusName;
}


public void setId(BigInteger value){
    this.id = value;
}


public void setNotificationTypeName(String value){
    this.notificationTypeName = value;
}


}