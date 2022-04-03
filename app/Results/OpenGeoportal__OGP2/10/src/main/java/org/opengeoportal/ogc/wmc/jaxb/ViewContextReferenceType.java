package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewContextReferenceType", namespace = "http://www.opengis.net/context", propOrder = { "title", "contextURL" })
public class ViewContextReferenceType {

@XmlElement(name = "Title", required = true)
 protected  String title;

@XmlElement(name = "ContextURL", required = true)
 protected  ContextURLType contextURL;

@XmlAttribute(required = true)
@XmlSchemaType(name = "anySimpleType")
 protected  String version;

@XmlAttribute(required = true)
@XmlSchemaType(name = "anySimpleType")
 protected  String id;


public String getVersion(){
    return version;
}


public String getTitle(){
    return title;
}


public ContextURLType getContextURL(){
    return contextURL;
}


public void setContextURL(ContextURLType value){
    this.contextURL = value;
}


public void setVersion(String value){
    this.version = value;
}


public void setTitle(String value){
    this.title = value;
}


public void setId(String value){
    this.id = value;
}


public String getId(){
    return id;
}


}