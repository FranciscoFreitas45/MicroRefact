package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryLogicOpType", namespace = "http://www.opengis.net/ogc", propOrder = { "comparisonOpsOrSpatialOpsOrLogicOps" })
public class BinaryLogicOpType extends LogicOpsType{

@XmlElementRefs({ @XmlElementRef(name = "logicOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class), @XmlElementRef(name = "comparisonOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class), @XmlElementRef(name = "spatialOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class) })
 protected  List<JAXBElement<?>> comparisonOpsOrSpatialOpsOrLogicOps;


public List<JAXBElement<?>> getComparisonOpsOrSpatialOpsOrLogicOps(){
    if (comparisonOpsOrSpatialOpsOrLogicOps == null) {
        comparisonOpsOrSpatialOpsOrLogicOps = new ArrayList<JAXBElement<?>>();
    }
    return this.comparisonOpsOrSpatialOpsOrLogicOps;
}


}