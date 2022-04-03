package org.opengeoportal.download.types.generated.ogc.wms_describelayer;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "layerDescription" })
@XmlRootElement(name = "WMS_DescribeLayerResponse")
public class WMSDescribeLayerResponse {

@XmlAttribute(required = true)
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String version;

@XmlElement(name = "LayerDescription")
 protected  List<LayerDescription> layerDescription;


public String getVersion(){
    return version;
}


public List<LayerDescription> getLayerDescription(){
    if (layerDescription == null) {
        layerDescription = new ArrayList<LayerDescription>();
    }
    return this.layerDescription;
}


public void setVersion(String value){
    this.version = value;
}


}