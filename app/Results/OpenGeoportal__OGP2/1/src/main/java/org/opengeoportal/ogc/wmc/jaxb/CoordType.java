package org.opengeoportal.ogc.wmc.jaxb;
 import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordType", namespace = "http://www.opengis.net/gml", propOrder = { "x", "y", "z" })
public class CoordType {

@XmlElement(name = "X", required = true)
 protected  BigDecimal x;

@XmlElement(name = "Y")
 protected  BigDecimal y;

@XmlElement(name = "Z")
 protected  BigDecimal z;


public BigDecimal getX(){
    return x;
}


public void setY(BigDecimal value){
    this.y = value;
}


public void setX(BigDecimal value){
    this.x = value;
}


public BigDecimal getY(){
    return y;
}


public BigDecimal getZ(){
    return z;
}


public void setZ(BigDecimal value){
    this.z = value;
}


}