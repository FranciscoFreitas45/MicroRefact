package org.opengeoportal.ogc.wmc.jaxb;
 import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingBoxType", namespace = "http://www.opengis.net/context")
public class BoundingBoxType {

@XmlAttribute(name = "SRS", required = true)
 protected  String srs;

@XmlAttribute(required = true)
 protected  BigDecimal minx;

@XmlAttribute(required = true)
 protected  BigDecimal miny;

@XmlAttribute(required = true)
 protected  BigDecimal maxx;

@XmlAttribute(required = true)
 protected  BigDecimal maxy;


public void setSRS(String value){
    this.srs = value;
}


public void setMiny(BigDecimal value){
    this.miny = value;
}


public void setMinx(BigDecimal value){
    this.minx = value;
}


public String getSRS(){
    return srs;
}


public BigDecimal getMaxx(){
    return maxx;
}


public BigDecimal getMaxy(){
    return maxy;
}


public void setMaxy(BigDecimal value){
    this.maxy = value;
}


public void setMaxx(BigDecimal value){
    this.maxx = value;
}


public BigDecimal getMinx(){
    return minx;
}


public BigDecimal getMiny(){
    return miny;
}


}