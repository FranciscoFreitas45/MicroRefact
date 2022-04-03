package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "geometry", "opacity", "channelSelection", "overlapBehavior", "colorMap", "contrastEnhancement", "shadedRelief", "imageOutline" })
public class RasterSymbolizer extends SymbolizerType{

@XmlElement(name = "Geometry")
 protected  Geometry geometry;

@XmlElement(name = "Opacity")
 protected  ParameterValueType opacity;

@XmlElement(name = "ChannelSelection")
 protected  ChannelSelection channelSelection;

@XmlElement(name = "OverlapBehavior")
 protected  OverlapBehavior overlapBehavior;

@XmlElement(name = "ColorMap")
 protected  ColorMap colorMap;

@XmlElement(name = "ContrastEnhancement")
 protected  ContrastEnhancement contrastEnhancement;

@XmlElement(name = "ShadedRelief")
 protected  ShadedRelief shadedRelief;

@XmlElement(name = "ImageOutline")
 protected  ImageOutline imageOutline;


public ContrastEnhancement getContrastEnhancement(){
    return contrastEnhancement;
}


public void setChannelSelection(ChannelSelection value){
    this.channelSelection = value;
}


public Geometry getGeometry(){
    return geometry;
}


public void setColorMap(ColorMap value){
    this.colorMap = value;
}


public void setGeometry(Geometry value){
    this.geometry = value;
}


public ChannelSelection getChannelSelection(){
    return channelSelection;
}


public ParameterValueType getOpacity(){
    return opacity;
}


public ColorMap getColorMap(){
    return colorMap;
}


public void setContrastEnhancement(ContrastEnhancement value){
    this.contrastEnhancement = value;
}


public ShadedRelief getShadedRelief(){
    return shadedRelief;
}


public OverlapBehavior getOverlapBehavior(){
    return overlapBehavior;
}


public ImageOutline getImageOutline(){
    return imageOutline;
}


public void setOverlapBehavior(OverlapBehavior value){
    this.overlapBehavior = value;
}


public void setImageOutline(ImageOutline value){
    this.imageOutline = value;
}


public void setOpacity(ParameterValueType value){
    this.opacity = value;
}


public void setShadedRelief(ShadedRelief value){
    this.shadedRelief = value;
}


}