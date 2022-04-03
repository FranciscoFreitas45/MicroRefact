package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionType", namespace = "http://www.opengis.net/context", propOrder = { "value" })
public class DimensionType {

@XmlValue
 protected  String value;

@XmlAttribute(required = true)
 protected  String name;

@XmlAttribute(required = true)
 protected  String units;

@XmlAttribute(required = true)
 protected  String unitSymbol;

@XmlAttribute(required = true)
 protected  String userValue;

@XmlAttribute(name = "default")
 protected  String _default;

@XmlAttribute
 protected  Boolean multipleValues;

@XmlAttribute
 protected  Boolean nearestValue;

@XmlAttribute
 protected  Boolean current;


public void setName(String value){
    this.name = value;
}


public Boolean isMultipleValues(){
    return multipleValues;
}


public void setNearestValue(Boolean value){
    this.nearestValue = value;
}


public String getName(){
    return name;
}


public Boolean isNearestValue(){
    return nearestValue;
}


public String getDefault(){
    return _default;
}


public void setDefault(String value){
    this._default = value;
}


public String getValue(){
    return value;
}


public String getUserValue(){
    return userValue;
}


public void setUserValue(String value){
    this.userValue = value;
}


public Boolean isCurrent(){
    return current;
}


public void setUnitSymbol(String value){
    this.unitSymbol = value;
}


public void setMultipleValues(Boolean value){
    this.multipleValues = value;
}


public void setValue(String value){
    this.value = value;
}


public String getUnitSymbol(){
    return unitSymbol;
}


public String getUnits(){
    return units;
}


public void setUnits(String value){
    this.units = value;
}


public void setCurrent(Boolean value){
    this.current = value;
}


}