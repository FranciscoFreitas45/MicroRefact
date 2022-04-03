package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.PropertyNameType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "propertyName" })
@XmlRootElement(name = "Geometry")
public class Geometry {

@XmlElement(name = "PropertyName", namespace = "http://www.opengis.net/ogc", required = true)
 protected  PropertyNameType propertyName;


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public PropertyNameType getPropertyName(){
    return propertyName;
}


}