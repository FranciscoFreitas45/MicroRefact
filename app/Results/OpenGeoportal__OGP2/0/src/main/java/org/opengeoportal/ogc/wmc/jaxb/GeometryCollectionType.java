package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometryCollectionType", namespace = "http://www.opengis.net/gml", propOrder = { "geometryMember" })
@XmlSeeAlso({ MultiPolygonType.class, MultiPointType.class, MultiLineStringType.class })
public class GeometryCollectionType extends AbstractGeometryCollectionBaseType{

@XmlElementRef(name = "geometryMember", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
 protected  List<JAXBElement<? extends GeometryAssociationType>> geometryMember;


public List<JAXBElement<? extends GeometryAssociationType>> getGeometryMember(){
    if (geometryMember == null) {
        geometryMember = new ArrayList<JAXBElement<? extends GeometryAssociationType>>();
    }
    return this.geometryMember;
}


}