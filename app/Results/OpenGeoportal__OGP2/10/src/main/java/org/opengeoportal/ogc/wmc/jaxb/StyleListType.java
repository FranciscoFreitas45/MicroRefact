package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StyleListType", namespace = "http://www.opengis.net/context", propOrder = { "style" })
public class StyleListType {

@XmlElement(name = "Style")
 protected  List<StyleType> style;


public List<StyleType> getStyle(){
    if (style == null) {
        style = new ArrayList<StyleType>();
    }
    return this.style;
}


}