package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "latestontop", "earliestontop", "average", "random" })
@XmlRootElement(name = "OverlapBehavior")
public class OverlapBehavior {

@XmlElement(name = "LATEST_ON_TOP")
 protected  LATESTONTOP latestontop;

@XmlElement(name = "EARLIEST_ON_TOP")
 protected  EARLIESTONTOP earliestontop;

@XmlElement(name = "AVERAGE")
 protected  AVERAGE average;

@XmlElement(name = "RANDOM")
 protected  RANDOM random;


public void setLATESTONTOP(LATESTONTOP value){
    this.latestontop = value;
}


public void setEARLIESTONTOP(EARLIESTONTOP value){
    this.earliestontop = value;
}


public RANDOM getRANDOM(){
    return random;
}


public void setAVERAGE(AVERAGE value){
    this.average = value;
}


public EARLIESTONTOP getEARLIESTONTOP(){
    return earliestontop;
}


public void setRANDOM(RANDOM value){
    this.random = value;
}


public AVERAGE getAVERAGE(){
    return average;
}


public LATESTONTOP getLATESTONTOP(){
    return latestontop;
}


}