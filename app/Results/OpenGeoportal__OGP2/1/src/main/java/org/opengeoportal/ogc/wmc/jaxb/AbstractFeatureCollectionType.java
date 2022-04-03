package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureCollectionType", namespace = "http://www.opengis.net/gml", propOrder = { "featureMember" })
public class AbstractFeatureCollectionType extends AbstractFeatureCollectionBaseType{

 protected  List<FeatureAssociationType> featureMember;


public List<FeatureAssociationType> getFeatureMember(){
    if (featureMember == null) {
        featureMember = new ArrayList<FeatureAssociationType>();
    }
    return this.featureMember;
}


}