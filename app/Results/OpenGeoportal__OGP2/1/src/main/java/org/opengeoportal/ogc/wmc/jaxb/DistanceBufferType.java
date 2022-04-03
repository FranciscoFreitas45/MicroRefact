package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistanceBufferType", namespace = "http://www.opengis.net/ogc", propOrder = { "propertyName", "geometry", "distance" })
public class DistanceBufferType extends SpatialOpsType{

@XmlElement(name = "PropertyName", required = true)
 protected  PropertyNameType propertyName;

@XmlElementRef(name = "_Geometry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
 protected  JAXBElement<? extends AbstractGeometryType> geometry;

@XmlElement(name = "Distance", required = true)
 protected  DistanceType distance;


public DistanceType getDistance(){
    return distance;
}


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public void setDistance(DistanceType value){
    this.distance = value;
}


public JAXBElement<? extends AbstractGeometryType> getGeometry(){
    return geometry;
}


public PropertyNameType getPropertyName(){
    return propertyName;
}


public void setGeometry(JAXBElement<? extends AbstractGeometryType> value){
    this.geometry = ((JAXBElement<? extends AbstractGeometryType>) value);
}


}