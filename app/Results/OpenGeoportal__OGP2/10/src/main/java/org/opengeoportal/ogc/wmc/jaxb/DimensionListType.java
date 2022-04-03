package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionListType", namespace = "http://www.opengis.net/context", propOrder = { "dimension" })
public class DimensionListType {

@XmlElement(name = "Dimension", required = true)
 protected  List<DimensionType> dimension;


public List<DimensionType> getDimension(){
    if (dimension == null) {
        dimension = new ArrayList<DimensionType>();
    }
    return this.dimension;
}


}