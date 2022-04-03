package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionType", namespace = "http://www.opengis.net/context", propOrder = { "any" })
public class ExtensionType {

@XmlAnyElement(lax = true)
 protected  Object any;


public void setAny(Object value){
    this.any = value;
}


public Object getAny(){
    return any;
}


}