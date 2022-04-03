package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureIdType", namespace = "http://www.opengis.net/ogc")
public class FeatureIdType {

@XmlAttribute(required = true)
@XmlSchemaType(name = "anyURI")
 protected  String fid;


public String getFid(){
    return fid;
}


public void setFid(String value){
    this.fid = value;
}


}