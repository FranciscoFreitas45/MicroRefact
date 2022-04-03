package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointType", namespace = "http://www.opengis.net/gml", propOrder = { "coord", "coordinates" })
public class PointType extends AbstractGeometryType{

 protected  CoordType coord;

 protected  CoordinatesType coordinates;


public void setCoord(CoordType value){
    this.coord = value;
}


public void setCoordinates(CoordinatesType value){
    this.coordinates = value;
}


public CoordType getCoord(){
    return coord;
}


public CoordinatesType getCoordinates(){
    return coordinates;
}


}