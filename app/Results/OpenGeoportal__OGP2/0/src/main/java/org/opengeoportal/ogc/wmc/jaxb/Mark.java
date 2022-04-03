package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "wellKnownName", "fill", "stroke" })
@XmlRootElement(name = "Mark")
public class Mark {

@XmlElement(name = "WellKnownName")
 protected  String wellKnownName;

@XmlElement(name = "Fill")
 protected  Fill fill;

@XmlElement(name = "Stroke")
 protected  Stroke stroke;


public String getWellKnownName(){
    return wellKnownName;
}


public Stroke getStroke(){
    return stroke;
}


public Fill getFill(){
    return fill;
}


public void setStroke(Stroke value){
    this.stroke = value;
}


public void setWellKnownName(String value){
    this.wellKnownName = value;
}


public void setFill(Fill value){
    this.fill = value;
}


}