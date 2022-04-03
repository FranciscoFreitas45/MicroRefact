package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "graphicFill", "graphicStroke", "cssParameter" })
@XmlRootElement(name = "Stroke")
public class Stroke {

@XmlElement(name = "GraphicFill")
 protected  GraphicFill graphicFill;

@XmlElement(name = "GraphicStroke")
 protected  GraphicStroke graphicStroke;

@XmlElement(name = "CssParameter")
 protected  List<CssParameter> cssParameter;


public void setGraphicStroke(GraphicStroke value){
    this.graphicStroke = value;
}


public GraphicStroke getGraphicStroke(){
    return graphicStroke;
}


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