package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "normalize", "histogram", "gammaValue" })
@XmlRootElement(name = "ContrastEnhancement")
public class ContrastEnhancement {

@XmlElement(name = "Normalize")
 protected  Normalize normalize;

@XmlElement(name = "Histogram")
 protected  Histogram histogram;

@XmlElement(name = "GammaValue")
 protected  Double gammaValue;


public Normalize getNormalize(){
    return normalize;
}


public void setNormalize(Normalize value){
    this.normalize = value;
}


public Double getGammaValue(){
    return gammaValue;
}


public void setHistogram(Histogram value){
    this.histogram = value;
}


public void setGammaValue(Double value){
    this.gammaValue = value;
}


public Histogram getHistogram(){
    return histogram;
}


}