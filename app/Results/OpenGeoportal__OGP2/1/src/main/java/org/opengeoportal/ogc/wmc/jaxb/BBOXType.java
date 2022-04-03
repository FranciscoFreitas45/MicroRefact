package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BBOXType", namespace = "http://www.opengis.net/ogc", propOrder = { "propertyName", "box" })
public class BBOXType extends SpatialOpsType{

@XmlElement(name = "PropertyName", required = true)
 protected  PropertyNameType propertyName;

@XmlElement(name = "Box", namespace = "http://www.opengis.net/gml", required = true)
 protected  BoxType box;


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public void setBox(BoxType value){
    this.box = value;
}


public BoxType getBox(){
    return box;
}


public PropertyNameType getPropertyName(){
    return propertyName;
}


}