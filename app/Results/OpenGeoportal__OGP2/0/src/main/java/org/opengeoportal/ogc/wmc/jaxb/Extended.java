package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extended", namespace = "http://www.w3.org/1999/xlink", propOrder = { "extendedModel" })
public class Extended {

@XmlElements({ @XmlElement(name = "resource", type = ResourceType.class), @XmlElement(name = "arc", type = ArcType.class), @XmlElement(name = "title", type = TitleEltType.class), @XmlElement(name = "locator", type = LocatorType.class) })
 protected  List<Object> extendedModel;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String role;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String title;


public List<Object> getExtendedModel(){
    if (extendedModel == null) {
        extendedModel = new ArrayList<Object>();
    }
    return this.extendedModel;
}


public String getTitle(){
    return title;
}


public TypeType getType(){
    if (type == null) {
        return TypeType.EXTENDED;
    } else {
        return type;
    }
}


public void setRole(String value){
    this.role = value;
}


public String getRole(){
    return role;
}


public void setTitle(String value){
    this.title = value;
}


public void setType(TypeType value){
    this.type = value;
}


}