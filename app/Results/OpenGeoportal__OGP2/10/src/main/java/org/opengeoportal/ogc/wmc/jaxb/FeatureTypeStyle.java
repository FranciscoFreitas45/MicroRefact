package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "title", "_abstract", "featureTypeName", "semanticTypeIdentifier", "rule" })
@XmlRootElement(name = "FeatureTypeStyle")
public class FeatureTypeStyle {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "FeatureTypeName")
 protected  String featureTypeName;

@XmlElement(name = "SemanticTypeIdentifier")
 protected  List<String> semanticTypeIdentifier;

@XmlElement(name = "Rule", required = true)
 protected  List<Rule> rule;


public void setName(String value){
    this.name = value;
}


public String getFeatureTypeName(){
    return featureTypeName;
}


public void setFeatureTypeName(String value){
    this.featureTypeName = value;
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public List<Rule> getRule(){
    if (rule == null) {
        rule = new ArrayList<Rule>();
    }
    return this.rule;
}


public List<String> getSemanticTypeIdentifier(){
    if (semanticTypeIdentifier == null) {
        semanticTypeIdentifier = new ArrayList<String>();
    }
    return this.semanticTypeIdentifier;
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


}