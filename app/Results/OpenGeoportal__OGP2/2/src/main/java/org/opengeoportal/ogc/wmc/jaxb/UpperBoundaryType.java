package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpperBoundaryType", namespace = "http://www.opengis.net/ogc", propOrder = { "expression" })
public class UpperBoundaryType {

@XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  JAXBElement<?> expression;


public JAXBElement<?> getExpression(){
    return expression;
}


public void setExpression(JAXBElement<?> value){
    this.expression = ((JAXBElement<?>) value);
}


}