package org.opengeoportal.ogc.wmc.jaxb;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "URLType", namespace = "http://www.opengis.net/context", propOrder = { "onlineResource" })
public class URLType {

@XmlElement(name = "OnlineResource", required = true)
 protected  OnlineResourceType onlineResource;

@XmlAttribute
 protected  BigInteger width;

@XmlAttribute
 protected  BigInteger height;

@XmlAttribute
 protected  String format;


public BigInteger getHeight(){
    return height;
}


public OnlineResourceType getOnlineResource(){
    return onlineResource;
}


public void setFormat(String value){
    this.format = value;
}


public void setOnlineResource(OnlineResourceType value){
    this.onlineResource = value;
}


public String getFormat(){
    return format;
}


public BigInteger getWidth(){
    return width;
}


public void setWidth(BigInteger value){
    this.width = value;
}


public void setHeight(BigInteger value){
    this.height = value;
}


}