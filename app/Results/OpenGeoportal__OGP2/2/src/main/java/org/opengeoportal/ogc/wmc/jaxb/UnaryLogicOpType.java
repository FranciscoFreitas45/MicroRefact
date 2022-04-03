package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnaryLogicOpType", namespace = "http://www.opengis.net/ogc", propOrder = { "comparisonOps", "spatialOps", "logicOps" })
public class UnaryLogicOpType extends LogicOpsType{

@XmlElementRef(name = "comparisonOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  JAXBElement<? extends ComparisonOpsType> comparisonOps;

@XmlElementRef(name = "spatialOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  JAXBElement<? extends SpatialOpsType> spatialOps;

@XmlElementRef(name = "logicOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  JAXBElement<? extends LogicOpsType> logicOps;


public JAXBElement<? extends ComparisonOpsType> getComparisonOps(){
    return comparisonOps;
}


public void setLogicOps(JAXBElement<? extends LogicOpsType> value){
    this.logicOps = ((JAXBElement<? extends LogicOpsType>) value);
}


public void setComparisonOps(JAXBElement<? extends ComparisonOpsType> value){
    this.comparisonOps = ((JAXBElement<? extends ComparisonOpsType>) value);
}


public JAXBElement<? extends SpatialOpsType> getSpatialOps(){
    return spatialOps;
}


public void setSpatialOps(JAXBElement<? extends SpatialOpsType> value){
    this.spatialOps = ((JAXBElement<? extends SpatialOpsType>) value);
}


public JAXBElement<? extends LogicOpsType> getLogicOps(){
    return logicOps;
}


}