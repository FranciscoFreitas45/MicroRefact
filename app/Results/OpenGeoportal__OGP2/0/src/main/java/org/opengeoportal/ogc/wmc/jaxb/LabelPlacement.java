package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pointPlacement", "linePlacement" })
@XmlRootElement(name = "LabelPlacement")
public class LabelPlacement {

@XmlElement(name = "PointPlacement")
 protected  PointPlacement pointPlacement;

@XmlElement(name = "LinePlacement")
 protected  LinePlacement linePlacement;


public PointPlacement getPointPlacement(){
    return pointPlacement;
}


public void setLinePlacement(LinePlacement value){
    this.linePlacement = value;
}


public void setPointPlacement(PointPlacement value){
    this.pointPlacement = value;
}


public LinePlacement getLinePlacement(){
    return linePlacement;
}


}