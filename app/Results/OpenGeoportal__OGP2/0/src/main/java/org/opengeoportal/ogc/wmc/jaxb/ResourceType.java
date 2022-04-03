package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resourceType", namespace = "http://www.w3.org/1999/xlink", propOrder = { "content" })
public class ResourceType {

@XmlMixed
@XmlAnyElement(lax = true)
 protected  List<Object> content;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String role;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String title;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
 protected  String label;


public String getTitle(){
    return title;
}


public String getLabel(){
    return label;
}


public TypeType getType(){
    if (type == null) {
        return TypeType.RESOURCE;
    } else {
        return type;
    }
}


public List<Object> getContent(){
    if (content == null) {
        content = new ArrayList<Object>();
    }
    return this.content;
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


public void setLabel(String value){
    this.label = value;
}


public void setType(TypeType value){
    this.type = value;
}


}