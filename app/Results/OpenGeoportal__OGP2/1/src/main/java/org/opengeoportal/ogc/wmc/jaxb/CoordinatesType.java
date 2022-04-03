package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinatesType", namespace = "http://www.opengis.net/gml", propOrder = { "value" })
public class CoordinatesType {

@XmlValue
 protected  String value;

@XmlAttribute
 protected  String decimal;

@XmlAttribute
 protected  String cs;

@XmlAttribute
 protected  String ts;


public String getValue(){
    return value;
}


public String getDecimal(){
    if (decimal == null) {
        return ".";
    } else {
        return decimal;
    }
}


public void setDecimal(String value){
    this.decimal = value;
}


public void setTs(String value){
    this.ts = value;
}


public void setCs(String value){
    this.cs = value;
}


public void setValue(String value){
    this.value = value;
}


public String getCs(){
    if (cs == null) {
        return ",";
    } else {
        return cs;
    }
}


public String getTs(){
    if (ts == null) {
        return " ";
    } else {
        return ts;
    }
}


}