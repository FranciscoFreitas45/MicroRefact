package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "onlineResource", "format" })
@XmlRootElement(name = "ExternalGraphic")
public class ExternalGraphic {

@XmlElement(name = "OnlineResource", required = true)
 protected  OnlineResource onlineResource;

@XmlElement(name = "Format", required = true)
 protected  String format;


public OnlineResource getOnlineResource(){
    return onlineResource;
}


public void setFormat(String value){
    this.format = value;
}


public void setOnlineResource(OnlineResource value){
    this.onlineResource = value;
}


public String getFormat(){
    return format;
}


}