package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistanceType", namespace = "http://www.opengis.net/ogc", propOrder = { "content" })
public class DistanceType {

@XmlValue
 protected  String content;

@XmlAttribute(required = true)
 protected  String units;


public void setContent(String value){
    this.content = value;
}


public String getContent(){
    return content;
}


public String getUnits(){
    return units;
}


public void setUnits(String value){
    this.units = value;
}


}