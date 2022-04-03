package org.opengeoportal.ogc.wmc.jaxb;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParameterValueType", propOrder = { "content" })
@XmlSeeAlso({ CssParameter.class })
public class ParameterValueType {

@XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
@XmlMixed
 protected  List<Serializable> content;


public List<Serializable> getContent(){
    if (content == null) {
        content = new ArrayList<Serializable>();
    }
    return this.content;
}


}