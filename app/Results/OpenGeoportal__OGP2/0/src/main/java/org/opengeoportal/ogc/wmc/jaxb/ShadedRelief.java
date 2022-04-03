package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "brightnessOnly", "reliefFactor" })
@XmlRootElement(name = "ShadedRelief")
public class ShadedRelief {

@XmlElement(name = "BrightnessOnly")
 protected  Boolean brightnessOnly;

@XmlElement(name = "ReliefFactor")
 protected  Double reliefFactor;


public Boolean isBrightnessOnly(){
    return brightnessOnly;
}


public Double getReliefFactor(){
    return reliefFactor;
}


public void setReliefFactor(Double value){
    this.reliefFactor = value;
}


public void setBrightnessOnly(Boolean value){
    this.brightnessOnly = value;
}


}