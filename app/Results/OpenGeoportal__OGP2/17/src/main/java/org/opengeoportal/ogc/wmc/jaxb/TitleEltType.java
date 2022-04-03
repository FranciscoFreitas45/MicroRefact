package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "titleEltType", namespace = "http://www.w3.org/1999/xlink", propOrder = { "content" })
public class TitleEltType {

@XmlMixed
@XmlAnyElement(lax = true)
 protected  List<Object> content;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
 protected  String lang;


public TypeType getType(){
    if (type == null) {
        return TypeType.TITLE;
    } else {
        return type;
    }
}


public String getLang(){
    return lang;
}


public List<Object> getContent(){
    if (content == null) {
        content = new ArrayList<Object>();
    }
    return this.content;
}


public void setType(TypeType value){
    this.type = value;
}


public void setLang(String value){
    this.lang = value;
}


}