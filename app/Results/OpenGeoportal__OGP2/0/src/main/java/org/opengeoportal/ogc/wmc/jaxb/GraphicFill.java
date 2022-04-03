package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "graphic" })
@XmlRootElement(name = "GraphicFill")
public class GraphicFill {

@XmlElement(name = "Graphic", required = true)
 protected  Graphic graphic;


public Graphic getGraphic(){
    return graphic;
}


public void setGraphic(Graphic value){
    this.graphic = value;
}


}