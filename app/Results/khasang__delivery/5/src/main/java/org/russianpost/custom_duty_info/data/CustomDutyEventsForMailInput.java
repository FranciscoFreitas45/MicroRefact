package org.russianpost.custom_duty_info.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomDutyEventsForMailInput")
public class CustomDutyEventsForMailInput {

@XmlAttribute(name = "Barcode", required = true)
 protected  String barcode;

@XmlAttribute(name = "Language")
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