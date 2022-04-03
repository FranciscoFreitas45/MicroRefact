package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerType", namespace = "http://www.opengis.net/context", propOrder = { "server", "name", "title", "_abstract", "dataURL", "metadataURL", "minScaleDenominator", "maxScaleDenominator", "srs", "formatList", "styleList", "dimensionList", "extension" })
public class LayerType {

@XmlElement(name = "Server", required = true)
 protected  ServerType server;

@XmlElement(name = "Name", required = true)
 protected  String name;

@XmlElement(name = "Title", required = true)
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "DataURL")
 protected  URLType dataURL;

@XmlElement(name = "MetadataURL")
 protected  URLType metadataURL;

@XmlElement(name = "MinScaleDenominator", namespace = "http://www.opengis.net/sld")
 protected  Double minScaleDenominator;

@XmlElement(name = "MaxScaleDenominator", namespace = "http://www.opengis.net/sld")
 protected  Double maxScaleDenominator;

@XmlElement(name = "SRS")
 protected  List<String> srs;

@XmlElement(name = "FormatList")
 protected  FormatListType formatList;

@XmlElement(name = "StyleList")
 protected  StyleListType styleList;

@XmlElement(name = "DimensionList")
 protected  DimensionListType dimensionList;

@XmlElement(name = "Extension")
 protected  ExtensionType extension;

@XmlAttribute(required = true)
 protected  boolean queryable;

@XmlAttribute(required = true)
 protected  boolean hidden;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public URLType getMetadataURL(){
    return metadataURL;
}


public void setExtension(ExtensionType value){
    this.extension = value;
}


public boolean isQueryable(){
    return queryable;
}


public void setServer(ServerType value){
    this.server = value;
}


public void setMetadataURL(URLType value){
    this.metadataURL = value;
}


public String getTitle(){
    return title;
}


public void setDimensionList(DimensionListType value){
    this.dimensionList = value;
}


public void setHidden(boolean value){
    this.hidden = value;
}


public void setFormatList(FormatListType value){
    this.formatList = value;
}


public void setMaxScaleDenominator(Double value){
    this.maxScaleDenominator = value;
}


public ServerType getServer(){
    return server;
}


public void setDataURL(URLType value){
    this.dataURL = value;
}


public FormatListType getFormatList(){
    return formatList;
}


public ExtensionType getExtension(){
    return extension;
}


public void setTitle(String value){
    this.title = value;
}


public Double getMaxScaleDenominator(){
    return maxScaleDenominator;
}


public boolean isHidden(){
    return hidden;
}


public DimensionListType getDimensionList(){
    return dimensionList;
}


public void setQueryable(boolean value){
    this.queryable = value;
}


public Double getMinScaleDenominator(){
    return minScaleDenominator;
}


public void setStyleList(StyleListType value){
    this.styleList = value;
}


public List<String> getSRS(){
    if (srs == null) {
        srs = new ArrayList<String>();
    }
    return this.srs;
}


public void setAbstract(String value){
    this._abstract = value;
}


public String getAbstract(){
    return _abstract;
}


public URLType getDataURL(){
    return dataURL;
}


public StyleListType getStyleList(){
    return styleList;
}


public void setMinScaleDenominator(Double value){
    this.minScaleDenominator = value;
}


}