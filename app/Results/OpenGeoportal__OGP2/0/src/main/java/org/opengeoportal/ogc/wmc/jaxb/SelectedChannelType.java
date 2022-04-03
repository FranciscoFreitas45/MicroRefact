package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectedChannelType", propOrder = { "sourceChannelName", "contrastEnhancement" })
public class SelectedChannelType {

@XmlElement(name = "SourceChannelName", required = true)
 protected  String sourceChannelName;

@XmlElement(name = "ContrastEnhancement")
 protected  ContrastEnhancement contrastEnhancement;


public String getSourceChannelName(){
    return sourceChannelName;
}


public ContrastEnhancement getContrastEnhancement(){
    return contrastEnhancement;
}


public void setSourceChannelName(String value){
    this.sourceChannelName = value;
}


public void setContrastEnhancement(ContrastEnhancement value){
    this.contrastEnhancement = value;
}


}