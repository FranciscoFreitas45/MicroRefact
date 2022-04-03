package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContextURLType", namespace = "http://www.opengis.net/context", propOrder = { "onlineResource" })
public class ContextURLType {

@XmlElement(name = "OnlineResource", required = true)
 protected  OnlineResourceType onlineResource;


public OnlineResourceType getOnlineResource(){
    return onlineResource;
}


public void setOnlineResource(OnlineResourceType value){
    this.onlineResource = value;
}


}