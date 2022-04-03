package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.PropertyNameType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsNullType", namespace = "http://www.opengis.net/ogc", propOrder = { "propertyName", "literal" })
public class PropertyIsNullType extends ComparisonOpsType{

@XmlElement(name = "PropertyName")
 protected  PropertyNameType propertyName;

@XmlElement(name = "Literal")
 protected  LiteralType literal;


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public void setLiteral(LiteralType value){
    this.literal = value;
}


public LiteralType getLiteral(){
    return literal;
}


public PropertyNameType getPropertyName(){
    return propertyName;
}


}