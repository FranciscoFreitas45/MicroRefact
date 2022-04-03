package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "title", "_abstract", "namedLayerOrUserLayer" })
@XmlRootElement(name = "StyledLayerDescriptor")
public class StyledLayerDescriptor {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElements({ @XmlElement(name = "UserLayer", type = UserLayer.class), @XmlElement(name = "NamedLayer", type = NamedLayer.class) })
 protected  List<Object> namedLayerOrUserLayer;

@XmlAttribute(required = true)
 protected  String version;


public void setName(String value){
    this.name = value;
}


public String getVersion(){
    if (version == null) {
        return "1.0.0";
    } else {
        return version;
    }
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public List<Object> getNamedLayerOrUserLayer(){
    if (namedLayerOrUserLayer == null) {
        namedLayerOrUserLayer = new ArrayList<Object>();
    }
    return this.namedLayerOrUserLayer;
}


public void setVersion(String value){
    this.version = value;
}


public void setAbstract(String value){
    this._abstract = value;
}


public void setTitle(String value){
    this.title = value;
}


public String getAbstract(){
    return _abstract;
}


}