package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "perpendicularOffset" })
@XmlRootElement(name = "LinePlacement")
public class LinePlacement {

@XmlElement(name = "PerpendicularOffset")
 protected  ParameterValueType perpendicularOffset;


public ParameterValueType getPerpendicularOffset(){
    return perpendicularOffset;
}


public void setPerpendicularOffset(ParameterValueType value){
    this.perpendicularOffset = value;
}


}