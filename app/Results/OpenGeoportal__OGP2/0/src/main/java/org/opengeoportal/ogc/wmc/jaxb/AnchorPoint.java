package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "anchorPointX", "anchorPointY" })
@XmlRootElement(name = "AnchorPoint")
public class AnchorPoint {

@XmlElement(name = "AnchorPointX", required = true)
 protected  ParameterValueType anchorPointX;

@XmlElement(name = "AnchorPointY", required = true)
 protected  ParameterValueType anchorPointY;


public ParameterValueType getAnchorPointY(){
    return anchorPointY;
}


public void setAnchorPointX(ParameterValueType value){
    this.anchorPointX = value;
}


public ParameterValueType getAnchorPointX(){
    return anchorPointX;
}


public void setAnchorPointY(ParameterValueType value){
    this.anchorPointY = value;
}


}