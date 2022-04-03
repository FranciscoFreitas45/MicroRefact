package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerType", namespace = "http://www.opengis.net/context", propOrder = { "onlineResource" })
public class ServerType {

@XmlElement(name = "OnlineResource", required = true)
 protected  OnlineResourceType onlineResource;

@XmlAttribute(required = true)
 protected  ServiceType service;

@XmlAttribute(required = true)
 protected  String version;

@XmlAttribute
 protected  String title;


public String getVersion(){
    return version;
}


public void setService(ServiceType value){
    this.service = value;
}


public String getTitle(){
    return title;
}


public OnlineResourceType getOnlineResource(){
    return onlineResource;
}


public void setVersion(String value){
    this.version = value;
}


public void setTitle(String value){
    this.title = value;
}


public void setOnlineResource(OnlineResourceType value){
    this.onlineResource = value;
}


public ServiceType getService(){
    return service;
}


}