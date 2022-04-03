package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "externalGraphicOrMark", "opacity", "size", "rotation" })
@XmlRootElement(name = "Graphic")
public class Graphic {

@XmlElements({ @XmlElement(name = "ExternalGraphic", type = ExternalGraphic.class), @XmlElement(name = "Mark", type = Mark.class) })
 protected  List<Object> externalGraphicOrMark;

@XmlElement(name = "Opacity")
 protected  ParameterValueType opacity;

@XmlElement(name = "Size")
 protected  ParameterValueType size;

@XmlElement(name = "Rotation")
 protected  ParameterValueType rotation;


public ParameterValueType getOpacity(){
    return opacity;
}


public ParameterValueType getSize(){
    return size;
}


public void setSize(ParameterValueType value){
    this.size = value;
}


public List<Object> getExternalGraphicOrMark(){
    if (externalGraphicOrMark == null) {
        externalGraphicOrMark = new ArrayList<Object>();
    }
    return this.externalGraphicOrMark;
}


public ParameterValueType getRotation(){
    return rotation;
}


public void setRotation(ParameterValueType value){
    this.rotation = value;
}


public void setOpacity(ParameterValueType value){
    this.opacity = value;
}


}