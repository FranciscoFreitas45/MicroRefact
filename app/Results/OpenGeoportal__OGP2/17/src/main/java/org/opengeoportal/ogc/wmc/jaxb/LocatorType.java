package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locatorType", namespace = "http://www.w3.org/1999/xlink", propOrder = { "title" })
public class LocatorType {

 protected  List<TitleEltType> title;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  String href;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String role;

@XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
 protected  String title1;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
 protected  String label;


public String getHref(){
    return href;
}


public List<TitleEltType> getTitle(){
    if (title == null) {
        title = new ArrayList<TitleEltType>();
    }
    return this.title;
}


public String getLabel(){
    return label;
}


public TypeType getType(){
    if (type == null) {
        return TypeType.LOCATOR;
    } else {
        return type;
    }
}


public void setHref(String value){
    this.href = value;
}


public void setRole(String value){
    this.role = value;
}


public void setTitle1(String value){
    this.title1 = value;
}


public String getRole(){
    return role;
}


public String getTitle1(){
    return title1;
}


public void setLabel(String value){
    this.label = value;
}


public void setType(TypeType value){
    this.type = value;
}


}