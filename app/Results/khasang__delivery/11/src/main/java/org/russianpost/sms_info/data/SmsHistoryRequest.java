package org.russianpost.sms_info.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "barcode", "language" })
@XmlRootElement(name = "SmsHistoryRequest")
public class SmsHistoryRequest {

@XmlElement(name = "Barcode", required = true)
 protected  String barcode;

@XmlElement(name = "Language", defaultValue = "RUS")
 protected  String language;


public String getBarcode(){
    return barcode;
}


public void setBarcode(String value){
    this.barcode = value;
}


public String getLanguage(){
    return language;
}


public void setLanguage(String value){
    this.language = value;
}


}