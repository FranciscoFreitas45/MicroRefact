package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "service", "onlineResource" })
@XmlRootElement(name = "RemoteOWS")
public class RemoteOWS {

@XmlElement(name = "Service", required = true)
 protected  String service;

@XmlElement(name = "OnlineResource", required = true)
 protected  OnlineResource onlineResource;


public void setService(String value){
    this.service = value;
}


public OnlineResource getOnlineResource(){
    return onlineResource;
}


public void setOnlineResource(OnlineResource value){
    this.onlineResource = value;
}


public String getService(){
    return service;
}


}