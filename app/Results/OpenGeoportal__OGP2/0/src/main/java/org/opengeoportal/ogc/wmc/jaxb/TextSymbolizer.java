package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "geometry", "label", "font", "labelPlacement", "halo", "fill" })
public class TextSymbolizer extends SymbolizerType{

@XmlElement(name = "Geometry")
 protected  Geometry geometry;

@XmlElement(name = "Label")
 protected  ParameterValueType label;

@XmlElement(name = "Font")
 protected  Font font;

@XmlElement(name = "LabelPlacement")
 protected  LabelPlacement labelPlacement;

@XmlElement(name = "Halo")
 protected  Halo halo;

@XmlElement(name = "Fill")
 protected  Fill fill;


public LabelPlacement getLabelPlacement(){
    return labelPlacement;
}


public Halo getHalo(){
    return halo;
}


public ParameterValueType getLabel(){
    return label;
}


public void setHalo(Halo value){
    this.halo = value;
}


public Fill getFill(){
    return fill;
}


public void setLabel(ParameterValueType value){
    this.label = value;
}


public Geometry getGeometry(){
    return geometry;
}


public Font getFont(){
    return font;
}


public void setFont(Font value){
    this.font = value;
}


public void setGeometry(Geometry value){
    this.geometry = value;
}


public void setLabelPlacement(LabelPlacement value){
    this.labelPlacement = value;
}


public void setFill(Fill value){
    this.fill = value;
}


}