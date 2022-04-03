package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "geometry", "graphic" })
public class PointSymbolizer extends SymbolizerType{

@XmlElement(name = "Geometry")
 protected  Geometry geometry;

@XmlElement(name = "Graphic")
 protected  Graphic graphic;


public Graphic getGraphic(){
    return graphic;
}


public void setGraphic(Graphic value){
    this.graphic = value;
}


public Geometry getGeometry(){
    return geometry;
}


public void setGeometry(Geometry value){
    this.geometry = value;
}


}