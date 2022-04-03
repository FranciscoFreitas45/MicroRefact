package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerListType", namespace = "http://www.opengis.net/context", propOrder = { "layer" })
public class LayerListType {

@XmlElement(name = "Layer", required = true)
 protected  List<LayerType> layer;


public List<LayerType> getLayer(){
    if (layer == null) {
        layer = new ArrayList<LayerType>();
    }
    return this.layer;
}


}