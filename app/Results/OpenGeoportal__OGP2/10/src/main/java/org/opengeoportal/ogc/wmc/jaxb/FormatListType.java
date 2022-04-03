package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormatListType", namespace = "http://www.opengis.net/context", propOrder = { "format" })
public class FormatListType {

@XmlElement(name = "Format", required = true)
 protected  List<FormatType> format;


public List<FormatType> getFormat(){
    if (format == null) {
        format = new ArrayList<FormatType>();
    }
    return this.format;
}


}