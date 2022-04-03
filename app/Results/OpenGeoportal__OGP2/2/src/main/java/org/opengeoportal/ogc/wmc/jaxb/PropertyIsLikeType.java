package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.PropertyNameType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsLikeType", namespace = "http://www.opengis.net/ogc", propOrder = { "propertyName", "literal" })
public class PropertyIsLikeType extends ComparisonOpsType{

@XmlElement(name = "PropertyName", required = true)
 protected  PropertyNameType propertyName;

@XmlElement(name = "Literal", required = true)
 protected  LiteralType literal;

@XmlAttribute(required = true)
 protected  String wildCard;

@XmlAttribute(required = true)
 protected  String singleChar;

@XmlAttribute(required = true)
 protected  String escape;


public String getWildCard(){
    return wildCard;
}


public void setSingleChar(String value){
    this.singleChar = value;
}


public void setPropertyName(PropertyNameType value){
    this.propertyName = value;
}


public void setLiteral(LiteralType value){
    this.literal = value;
}


public LiteralType getLiteral(){
    return literal;
}


public String getEscape(){
    return escape;
}


public void setWildCard(String value){
    this.wildCard = value;
}


public String getSingleChar(){
    return singleChar;
}


public void setEscape(String value){
    this.escape = value;
}


public PropertyNameType getPropertyName(){
    return propertyName;
}


}