package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "title", "_abstract", "isDefault", "featureTypeStyle" })
@XmlRootElement(name = "UserStyle")
public class UserStyle {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "IsDefault")
 protected  Boolean isDefault;

@XmlElement(name = "FeatureTypeStyle", required = true)
 protected  List<FeatureTypeStyle> featureTypeStyle;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public void setIsDefault(Boolean value){
    this.isDefault = value;
}


public List<FeatureTypeStyle> getFeatureTypeStyle(){
    if (featureTypeStyle == null) {
        featureTypeStyle = new ArrayList<FeatureTypeStyle>();
    }
    return this.featureTypeStyle;
}


public void setAbstract(String value){
    this._abstract = value;
}


public void setTitle(String value){
    this.title = value;
}


public String getAbstract(){
    return _abstract;
}


public Boolean isIsDefault(){
    return isDefault;
}


}