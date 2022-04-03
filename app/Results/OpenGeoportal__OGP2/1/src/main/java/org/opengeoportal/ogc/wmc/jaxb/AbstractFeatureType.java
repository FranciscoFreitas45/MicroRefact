package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureType", namespace = "http://www.opengis.net/gml", propOrder = { "description", "name", "boundedBy" })
@XmlSeeAlso({ AbstractFeatureCollectionBaseType.class })
public class AbstractFeatureType {

 protected  String description;

 protected  String name;

 protected  BoundingShapeType boundedBy;

@XmlAttribute
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
@XmlID
@XmlSchemaType(name = "ID")
 protected  String fid;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public String getFid(){
    return fid;
}


public void setBoundedBy(BoundingShapeType value){
    this.boundedBy = value;
}


public void setDescription(String value){
    this.description = value;
}


public String getDescription(){
    return description;
}


public void setFid(String value){
    this.fid = value;
}


public BoundingShapeType getBoundedBy(){
    return boundedBy;
}


}