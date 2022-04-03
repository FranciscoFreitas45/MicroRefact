package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cssParameter" })
@XmlRootElement(name = "Font")
public class Font {

@XmlElement(name = "CssParameter")
 protected  List<CssParameter> cssParameter;


public List<CssParameter> getCssParameter(){
    if (cssParameter == null) {
        cssParameter = new ArrayList<CssParameter>();
    }
    return this.cssParameter;
}


}