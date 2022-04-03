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
@XmlType(name = "", propOrder = { "name", "layerFeatureConstraints", "namedStyleOrUserStyle" })
@XmlRootElement(name = "NamedLayer")
public class NamedLayer {

@XmlElement(name = "Name", required = true)
 protected  String name;

@XmlElement(name = "LayerFeatureConstraints")
 protected  LayerFeatureConstraints layerFeatureConstraints;

@XmlElements({ @XmlElement(name = "UserStyle", type = UserStyle.class), @XmlElement(name = "NamedStyle", type = NamedStyle.class) })
 protected  List<Object> namedStyleOrUserStyle;


public void setName(String value){
    this.name = value;
}


public LayerFeatureConstraints getLayerFeatureConstraints(){
    return layerFeatureConstraints;
}


public String getName(){
    return name;
}


public void setLayerFeatureConstraints(LayerFeatureConstraints value){
    this.layerFeatureConstraints = value;
}


public List<Object> getNamedStyleOrUserStyle(){
    if (namedStyleOrUserStyle == null) {
        namedStyleOrUserStyle = new ArrayList<Object>();
    }
    return this.namedStyleOrUserStyle;
}


}