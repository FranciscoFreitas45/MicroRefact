package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "colorMapEntry" })
@XmlRootElement(name = "ColorMap")
public class ColorMap {

@XmlElement(name = "ColorMapEntry")
 protected  List<ColorMapEntry> colorMapEntry;


public List<ColorMapEntry> getColorMapEntry(){
    if (colorMapEntry == null) {
        colorMapEntry = new ArrayList<ColorMapEntry>();
    }
    return this.colorMapEntry;
}


}