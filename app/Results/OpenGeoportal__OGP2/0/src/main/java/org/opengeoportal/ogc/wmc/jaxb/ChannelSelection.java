package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "redChannel", "greenChannel", "blueChannel", "grayChannel" })
@XmlRootElement(name = "ChannelSelection")
public class ChannelSelection {

@XmlElement(name = "RedChannel")
 protected  SelectedChannelType redChannel;

@XmlElement(name = "GreenChannel")
 protected  SelectedChannelType greenChannel;

@XmlElement(name = "BlueChannel")
 protected  SelectedChannelType blueChannel;

@XmlElement(name = "GrayChannel")
 protected  SelectedChannelType grayChannel;


public void setBlueChannel(SelectedChannelType value){
    this.blueChannel = value;
}


public SelectedChannelType getGreenChannel(){
    return greenChannel;
}


public SelectedChannelType getGrayChannel(){
    return grayChannel;
}


public SelectedChannelType getRedChannel(){
    return redChannel;
}


public void setRedChannel(SelectedChannelType value){
    this.redChannel = value;
}


public void setGreenChannel(SelectedChannelType value){
    this.greenChannel = value;
}


public void setGrayChannel(SelectedChannelType value){
    this.grayChannel = value;
}


public SelectedChannelType getBlueChannel(){
    return blueChannel;
}


}