package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FunctionType", namespace = "http://www.opengis.net/ogc", propOrder = { "expression" })
public class FunctionType extends ExpressionType{

@XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
 protected  List<JAXBElement<?>> expression;

@XmlAttribute(required = true)
 protected  String name;


public void setName(String value){
    this.name = value;
}


public List<JAXBElement<?>> getExpression(){
    if (expression == null) {
        expression = new ArrayList<JAXBElement<?>>();
    }
    return this.expression;
}


public String getName(){
    return name;
}


}