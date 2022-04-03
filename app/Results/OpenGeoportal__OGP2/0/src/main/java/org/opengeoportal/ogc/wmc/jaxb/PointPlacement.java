package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "anchorPoint", "displacement", "rotation" })
@XmlRootElement(name = "PointPlacement")
public class PointPlacement {

@XmlElement(name = "AnchorPoint")
 protected  AnchorPoint anchorPoint;

@XmlElement(name = "Displacement")
 protected  Displacement displacement;

@XmlElement(name = "Rotation")
 protected  ParameterValueType rotation;


public AnchorPoint getAnchorPoint(){
    return anchorPoint;
}


public void setDisplacement(Displacement value){
    this.displacement = value;
}


public void setAnchorPoint(AnchorPoint value){
    this.anchorPoint = value;
}


public ParameterValueType getRotation(){
    return rotation;
}


public void setRotation(ParameterValueType value){
    this.rotation = value;
}


public Displacement getDisplacement(){
    return displacement;
}


}