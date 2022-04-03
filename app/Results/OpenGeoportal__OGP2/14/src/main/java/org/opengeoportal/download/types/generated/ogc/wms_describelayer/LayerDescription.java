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
@XmlType(name = "", propOrder = { "query" })
@XmlRootElement(name = "LayerDescription")
public class LayerDescription {

@XmlAttribute(required = true)
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String name;

@XmlAttribute
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String wfs;

@XmlAttribute
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String owsType;

@XmlAttribute
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String owsURL;

@XmlElement(name = "Query")
 protected  List<Query> query;


public void setName(String value){
    this.name = value;
}


public void setWfs(String value){
    this.wfs = value;
}


public String getOwsType(){
    return owsType;
}


public String getName(){
    return name;
}


public String getWfs(){
    return wfs;
}


public String getOwsURL(){
    return owsURL;
}


public void setOwsType(String value){
    this.owsType = value;
}


public void setOwsURL(String value){
    this.owsURL = value;
}


public List<Query> getQuery(){
    if (query == null) {
        query = new ArrayList<Query>();
    }
    return this.query;
}


}