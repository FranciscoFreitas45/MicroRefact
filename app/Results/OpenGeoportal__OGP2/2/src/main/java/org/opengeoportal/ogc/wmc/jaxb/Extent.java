package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "value" })
@XmlRootElement(name = "Extent")
public class Extent {

@XmlElement(name = "Name", required = true)
 protected  String name;

@XmlElement(name = "Value", required = true)
 protected  String value;


public void setName(String value){
    this.name = value;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public void setValue(String value){
    this.value = value;
}


}