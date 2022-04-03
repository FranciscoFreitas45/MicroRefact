package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StyleType", namespace = "http://www.opengis.net/context", propOrder = { "name", "title", "_abstract", "legendURL", "sld" })
public class StyleType {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "LegendURL")
 protected  URLType legendURL;

@XmlElement(name = "SLD")
 protected  SLDType sld;

@XmlAttribute
 protected  Boolean current;


public void setName(String value){
    this.name = value;
}


public Boolean isCurrent(){
    return current;
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public URLType getLegendURL(){
    return legendURL;
}


public void setAbstract(String value){
    this._abstract = value;
}


public void setTitle(String value){
    this.title = value;
}


public void setSLD(SLDType value){
    this.sld = value;
}


public String getAbstract(){
    return _abstract;
}


public SLDType getSLD(){
    return sld;
}


public void setCurrent(Boolean value){
    this.current = value;
}


public void setLegendURL(URLType value){
    this.legendURL = value;
}


}