package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsBetweenType", namespace = "http://www.opengis.net/ogc", propOrder = { "expression", "lowerBoundary", "upperBoundary" })
public class PropertyIsBetweenType extends ComparisonOpsType{

@XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  JAXBElement<?> expression;

@XmlElement(name = "LowerBoundary", required = true)
 protected  LowerBoundaryType lowerBoundary;

@XmlElement(name = "UpperBoundary", required = true)
 protected  UpperBoundaryType upperBoundary;


public LowerBoundaryType getLowerBoundary(){
    return lowerBoundary;
}


public JAXBElement<?> getExpression(){
    return expression;
}


public void setLowerBoundary(LowerBoundaryType value){
    this.lowerBoundary = value;
}


public UpperBoundaryType getUpperBoundary(){
    return upperBoundary;
}


public void setExpression(JAXBElement<?> value){
    this.expression = ((JAXBElement<?>) value);
}


public void setUpperBoundary(UpperBoundaryType value){
    this.upperBoundary = value;
}


}