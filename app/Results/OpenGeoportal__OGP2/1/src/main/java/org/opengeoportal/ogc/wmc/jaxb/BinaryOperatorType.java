package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryOperatorType", namespace = "http://www.opengis.net/ogc", propOrder = { "expression" })
public class BinaryOperatorType extends ExpressionType{

@XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  List<JAXBElement<?>> expression;


public List<JAXBElement<?>> getExpression(){
    if (expression == null) {
        expression = new ArrayList<JAXBElement<?>>();
    }
    return this.expression;
}


}