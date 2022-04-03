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
@XmlType(name = "arcType", namespace = "http://www.w3.org/1999/xlink", propOrder = { "title" })
public class ArcType {

 protected  List<TitleEltType> title;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink", required = true)
 protected  TypeType type;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  String arcrole;

@XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
 protected  String title2;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  ShowType show;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
 protected  ActuateType actuate;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
 protected  String from;

@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
 protected  String to;


public void setTitle2(String value){
    this.title2 = value;
}


public void setFrom(String value){
    this.from = value;
}


public String getArcrole(){
    return arcrole;
}


public void setActuate(ActuateType value){
    this.actuate = value;
}


public void setArcrole(String value){
    this.arcrole = value;
}


public String getTo(){
    return to;
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


public List<TitleEltType> getTitle(){
    if (title == null) {
        title = new ArrayList<TitleEltType>();
    }
    return this.title;
}


public TypeType getType(){
    if (type == null) {
        return TypeType.ARC;
    } else {
        return type;
    }
}


public ShowType getShow(){
    return show;
}


public String getTitle2(){
    return title2;
}


public void setTo(String value){
    this.to = value;
}


public String getFrom(){
    return from;
}


}