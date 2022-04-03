package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "OnlineResource")
public class OnlineResource {

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


public void setShow(ShowType value){
    this.show = value;
}


public String getTitle(){
    return title;
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