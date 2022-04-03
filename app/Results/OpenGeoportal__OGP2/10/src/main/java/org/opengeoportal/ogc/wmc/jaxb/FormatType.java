package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormatType", namespace = "http://www.opengis.net/context", propOrder = { "value" })
public class FormatType {

@XmlValue
 protected  String value;

@XmlAttribute
 protected  Boolean current;


public String getValue(){
    return value;
}


public Boolean isCurrent(){
    return current;
}


public void setValue(String value){
    this.value = value;
}


public void setCurrent(Boolean value){
    this.current = value;
}


}