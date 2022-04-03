package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opengis.net/context", propOrder = { "general", "layerList" })
@XmlRootElement(name = "ViewContext")
public class ViewContextType {

@XmlElement(name = "General", required = true)
 protected  GeneralType general;

@XmlElement(name = "LayerList", required = true)
 protected  LayerListType layerList;

@XmlAttribute(required = true)
 protected  String version;

@XmlAttribute(required = true)
 protected  String id;


public String getVersion(){
    if (version == null) {
        return "1.1.0";
    } else {
        return version;
    }
}


public void setGeneral(GeneralType value){
    this.general = value;
}


public GeneralType getGeneral(){
    return general;
}


public void setLayerList(LayerListType value){
    this.layerList = value;
}


public void setVersion(String value){
    this.version = value;
}


public void setId(String value){
    this.id = value;
}


public String getId(){
    return id;
}


public LayerListType getLayerList(){
    return layerList;
}


}