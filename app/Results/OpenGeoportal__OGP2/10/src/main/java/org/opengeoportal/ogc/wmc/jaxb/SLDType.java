package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLDType", namespace = "http://www.opengis.net/context", propOrder = { "name", "title", "legendURL", "onlineResource", "styledLayerDescriptor", "featureTypeStyle" })
public class SLDType {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "LegendURL")
 protected  URLType legendURL;

@XmlElement(name = "OnlineResource")
 protected  OnlineResourceType onlineResource;

@XmlElement(name = "StyledLayerDescriptor", namespace = "http://www.opengis.net/sld")
 protected  StyledLayerDescriptor styledLayerDescriptor;

@XmlElement(name = "FeatureTypeStyle", namespace = "http://www.opengis.net/sld")
 protected  FeatureTypeStyle featureTypeStyle;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public OnlineResourceType getOnlineResource(){
    return onlineResource;
}


public URLType getLegendURL(){
    return legendURL;
}


public void setStyledLayerDescriptor(StyledLayerDescriptor value){
    this.styledLayerDescriptor = value;
}


public FeatureTypeStyle getFeatureTypeStyle(){
    return featureTypeStyle;
}


public void setTitle(String value){
    this.title = value;
}


public void setOnlineResource(OnlineResourceType value){
    this.onlineResource = value;
}


public void setLegendURL(URLType value){
    this.legendURL = value;
}


public StyledLayerDescriptor getStyledLayerDescriptor(){
    return styledLayerDescriptor;
}


public void setFeatureTypeStyle(FeatureTypeStyle value){
    this.featureTypeStyle = value;
}


}