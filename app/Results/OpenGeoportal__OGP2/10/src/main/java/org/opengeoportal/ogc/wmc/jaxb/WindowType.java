package org.opengeoportal.ogc.wmc.jaxb;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WindowType", namespace = "http://www.opengis.net/context")
public class WindowType {

@XmlAttribute(required = true)
 protected  BigInteger width;

@XmlAttribute(required = true)
 protected  BigInteger height;


public BigInteger getHeight(){
    return height;
}


public BigInteger getWidth(){
    return width;
}


public void setWidth(BigInteger value){
    this.width = value;
}


public void setHeight(BigInteger value){
    this.height = value;
}


}