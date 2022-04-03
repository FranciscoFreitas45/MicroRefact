package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiteralType", namespace = "http://www.opengis.net/ogc", propOrder = { "content" })
public class LiteralType {

@XmlMixed
@XmlAnyElement(lax = true)
 protected  List<Object> content;


public List<Object> getContent(){
    if (content == null) {
        content = new ArrayList<Object>();
    }
    return this.content;
}


}