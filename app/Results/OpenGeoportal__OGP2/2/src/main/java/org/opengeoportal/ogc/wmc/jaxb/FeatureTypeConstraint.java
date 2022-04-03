package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "featureTypeName", "filter", "extent" })
@XmlRootElement(name = "FeatureTypeConstraint")
public class FeatureTypeConstraint {

@XmlElement(name = "FeatureTypeName")
 protected  String featureTypeName;

@XmlElement(name = "Filter", namespace = "http://www.opengis.net/ogc")
 protected  FilterType filter;

@XmlElement(name = "Extent")
 protected  List<Extent> extent;


public String getFeatureTypeName(){
    return featureTypeName;
}


public FilterType getFilter(){
    return filter;
}


public List<Extent> getExtent(){
    if (extent == null) {
        extent = new ArrayList<Extent>();
    }
    return this.extent;
}


public void setFeatureTypeName(String value){
    this.featureTypeName = value;
}


public void setFilter(FilterType value){
    this.filter = value;
}


}