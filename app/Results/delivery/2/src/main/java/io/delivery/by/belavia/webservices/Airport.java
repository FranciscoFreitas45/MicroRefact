package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Airport")
public class Airport {

@XmlAttribute(name = "IATA")
 protected  String iata;

@XmlAttribute(name = "Name")
 protected  String name;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public String getIATA(){
    return iata;
}


public void setIATA(String value){
    this.iata = value;
}


}