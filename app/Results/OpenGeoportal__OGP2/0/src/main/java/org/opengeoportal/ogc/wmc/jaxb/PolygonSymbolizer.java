package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "geometry", "fill", "stroke" })
public class PolygonSymbolizer extends SymbolizerType{

@XmlElement(name = "Geometry")
 protected  Geometry geometry;

@XmlElement(name = "Fill")
 protected  Fill fill;

@XmlElement(name = "Stroke")
 protected  Stroke stroke;


public Stroke getStroke(){
    return stroke;
}


public Fill getFill(){
    return fill;
}


public Geometry getGeometry(){
    return geometry;
}


public void setStroke(Stroke value){
    this.stroke = value;
}


public void setGeometry(Geometry value){
    this.geometry = value;
}


public void setFill(Fill value){
    this.fill = value;
}


}