package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonType", namespace = "http://www.opengis.net/gml", propOrder = { "outerBoundaryIs", "innerBoundaryIs" })
public class PolygonType extends AbstractGeometryType{

@XmlElement(required = true)
 protected  LinearRingMemberType outerBoundaryIs;

 protected  List<LinearRingMemberType> innerBoundaryIs;


public void setOuterBoundaryIs(LinearRingMemberType value){
    this.outerBoundaryIs = value;
}


public LinearRingMemberType getOuterBoundaryIs(){
    return outerBoundaryIs;
}


public List<LinearRingMemberType> getInnerBoundaryIs(){
    if (innerBoundaryIs == null) {
        innerBoundaryIs = new ArrayList<LinearRingMemberType>();
    }
    return this.innerBoundaryIs;
}


}