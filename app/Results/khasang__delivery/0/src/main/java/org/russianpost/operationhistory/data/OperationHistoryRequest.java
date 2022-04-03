package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "barcode", "messageType", "language" })
@XmlRootElement(name = "OperationHistoryRequest")
public class OperationHistoryRequest {

@XmlElement(name = "Barcode", required = true)
 protected  String barcode;

@XmlElement(name = "MessageType")
 protected  int messageType;

@XmlElement(name = "Language", defaultValue = "RUS")
 protected  String language;


public int getMessageType(){
    return messageType;
}


public String getBarcode(){
    return barcode;
}


public void setBarcode(String value){
    this.barcode = value;
}


public String getLanguage(){
    return language;
}


public void setMessageType(int value){
    this.messageType = value;
}


public void setLanguage(String value){
    this.language = value;
}


}