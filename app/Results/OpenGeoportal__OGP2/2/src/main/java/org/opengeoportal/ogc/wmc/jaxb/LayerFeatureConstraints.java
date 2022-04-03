package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "featureTypeConstraint" })
@XmlRootElement(name = "LayerFeatureConstraints")
public class LayerFeatureConstraints {

@XmlElement(name = "FeatureTypeConstraint", required = true)
 protected  List<FeatureTypeConstraint> featureTypeConstraint;


public List<FeatureTypeConstraint> getFeatureTypeConstraint(){
    if (featureTypeConstraint == null) {
        featureTypeConstraint = new ArrayList<FeatureTypeConstraint>();
    }
    return this.featureTypeConstraint;
}


}