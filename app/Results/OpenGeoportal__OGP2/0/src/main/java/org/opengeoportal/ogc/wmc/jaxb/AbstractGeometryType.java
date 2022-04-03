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
@XmlType(name = "AbstractGeometryType", namespace = "http://www.opengis.net/gml")
@XmlSeeAlso({ LinearRingType.class, PointType.class, LineStringType.class, BoxType.class, PolygonType.class, AbstractGeometryCollectionBaseType.class })
public class AbstractGeometryType {

@XmlAttribute
@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
@XmlID
@XmlSchemaType(name = "ID")
 protected  String gid;

@XmlAttribute
@XmlSchemaType(name = "anyURI")
 protected  String srsName;


public void setSrsName(String value){
    this.srsName = value;
}


public void setGid(String value){
    this.gid = value;
}


public String getSrsName(){
    return srsName;
}


public String getGid(){
    return gid;
}


}