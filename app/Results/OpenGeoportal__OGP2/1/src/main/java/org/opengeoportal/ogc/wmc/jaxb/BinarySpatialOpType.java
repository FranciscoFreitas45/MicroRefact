package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinarySpatialOpType", namespace = "http://www.opengis.net/ogc", propOrder = { "propertyName", "geometry", "box" })
public class BinarySpatialOpType extends SpatialOpsType{

@XmlElement(name = "PropertyName", required = true)
 protected  PropertyNameType propertyName;

@XmlElementRef(name = "_Geometry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
 protected  JAXBElement<? extends AbstractGeometryType> geometry;

@XmlElement(name = "Box", namespace = "http://www.opengis.net/gml")
 protected  BoxType box;


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public JAXBElement<? extends AbstractGeometryType> getGeometry(){
    return geometry;
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


public void setGeometry(JAXBElement<? extends AbstractGeometryType> value){
    this.geometry = ((JAXBElement<? extends AbstractGeometryType>) value);
}


}