package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureAssociationType", namespace = "http://www.opengis.net/gml", propOrder = { "feature" })
public class FeatureAssociationType {

@XmlElementRef(name = "_Feature", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
 protected  JAXBElement<? extends AbstractFeatureType> feature;

@XmlAttribute(namespace = "http://www.opengis.net/gml")
@XmlSchemaType(name = "anyURI")
 protected  String remoteSchema;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String href;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String role;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String arcrole;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String title;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  ShowType show;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  ActuateType actuate;


public String getRemoteSchema(){
    return remoteSchema;
}


public String getHref(){
    return href;
}


public void setHref(String value){
    this.href = value;
}


public String getRole(){
    return role;
}


public String getArcrole(){
    return arcrole;
}


public void setTitle(String value){
    this.title = value;
}


public void setActuate(ActuateType value){
    this.actuate = value;
}


public void setArcrole(String value){
    this.arcrole = value;
}


public ActuateType getActuate(){
    return actuate;
}


public void setType(TypeType value){
    this.type = value;
}


public JAXBElement<? extends AbstractFeatureType> getFeature(){
    return feature;
}


public void setRemoteSchema(String value){
    this.remoteSchema = value;
}


public void setShow(ShowType value){
    this.show = value;
}


public String getTitle(){
    return title;
}


public void setFeature(JAXBElement<? extends AbstractFeatureType> value){
    this.feature = ((JAXBElement<? extends AbstractFeatureType>) value);
}


public TypeType getType(){
    if (type == null) {
        return TypeType.SIMPLE;
    } else {
        return type;
    }
}


public ShowType getShow(){
    return show;
}


public void setRole(String value){
    this.role = value;
}


}