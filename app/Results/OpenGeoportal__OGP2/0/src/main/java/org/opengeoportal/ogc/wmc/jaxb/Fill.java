package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "graphicFill", "cssParameter" })
@XmlRootElement(name = "Fill")
public class Fill {

@XmlElement(name = "GraphicFill")
 protected  GraphicFill graphicFill;

@XmlElement(name = "CssParameter")
 protected  List<CssParameter> cssParameter;


public void setGraphicFill(GraphicFill value){
    this.graphicFill = value;
}


public GraphicFill getGraphicFill(){
    return graphicFill;
}


public List<CssParameter> getCssParameter(){
    if (cssParameter == null) {
        cssParameter = new ArrayList<CssParameter>();
    }
    return this.cssParameter;
}


}