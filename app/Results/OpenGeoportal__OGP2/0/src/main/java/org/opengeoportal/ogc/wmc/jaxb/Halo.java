package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "radius", "fill" })
@XmlRootElement(name = "Halo")
public class Halo {

@XmlElement(name = "Radius")
 protected  ParameterValueType radius;

@XmlElement(name = "Fill")
 protected  Fill fill;


public Fill getFill(){
    return fill;
}


public ParameterValueType getRadius(){
    return radius;
}


public void setRadius(ParameterValueType value){
    this.radius = value;
}


public void setFill(Fill value){
    this.fill = value;
}


}