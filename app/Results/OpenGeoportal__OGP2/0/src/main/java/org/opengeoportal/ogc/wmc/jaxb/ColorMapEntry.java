package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ColorMapEntry")
public class ColorMapEntry {

@XmlAttribute(required = true)
 protected  String color;

@XmlAttribute
 protected  Double opacity;

@XmlAttribute
 protected  Double quantity;

@XmlAttribute
 protected  String label;


public Double getOpacity(){
    return opacity;
}


public void setColor(String value){
    this.color = value;
}


public Double getQuantity(){
    return quantity;
}


public String getLabel(){
    return label;
}


public void setQuantity(Double value){
    this.quantity = value;
}


public String getColor(){
    return color;
}


public void setLabel(String value){
    this.label = value;
}


public void setOpacity(Double value){
    this.opacity = value;
}


}