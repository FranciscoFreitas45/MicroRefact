package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "displacementX", "displacementY" })
@XmlRootElement(name = "Displacement")
public class Displacement {

@XmlElement(name = "DisplacementX", required = true)
 protected  ParameterValueType displacementX;

@XmlElement(name = "DisplacementY", required = true)
 protected  ParameterValueType displacementY;


public ParameterValueType getDisplacementX(){
    return displacementX;
}


public void setDisplacementY(ParameterValueType value){
    this.displacementY = value;
}


public ParameterValueType getDisplacementY(){
    return displacementY;
}


public void setDisplacementX(ParameterValueType value){
    this.displacementX = value;
}


}