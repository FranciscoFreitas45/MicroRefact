package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "lineSymbolizer", "polygonSymbolizer" })
@XmlRootElement(name = "ImageOutline")
public class ImageOutline {

@XmlElement(name = "LineSymbolizer")
 protected  LineSymbolizer lineSymbolizer;

@XmlElement(name = "PolygonSymbolizer")
 protected  PolygonSymbolizer polygonSymbolizer;


public void setPolygonSymbolizer(PolygonSymbolizer value){
    this.polygonSymbolizer = value;
}


public void setLineSymbolizer(LineSymbolizer value){
    this.lineSymbolizer = value;
}


public LineSymbolizer getLineSymbolizer(){
    return lineSymbolizer;
}


public PolygonSymbolizer getPolygonSymbolizer(){
    return polygonSymbolizer;
}


}