package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingShapeType", namespace = "http://www.opengis.net/gml", propOrder = { "box", "_null" })
public class BoundingShapeType {

@XmlElement(name = "Box")
 protected  BoxType box;

@XmlElement(name = "null")
 protected  NullType _null;


public void setNull(NullType value){
    this._null = value;
}


public void setBox(BoxType value){
    this.box = value;
}


public BoxType getBox(){
    return box;
}


public NullType getNull(){
    return _null;
}


}