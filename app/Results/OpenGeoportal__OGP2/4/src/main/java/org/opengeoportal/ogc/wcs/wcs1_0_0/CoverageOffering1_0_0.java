package org.opengeoportal.ogc.wcs.wcs1_0_0;
 import java.util.List;
import org.opengeoportal.layer.Envelope;
import org.opengeoportal.ogc.OwsDescribeInfo;
public class CoverageOffering1_0_0 implements OwsDescribeInfo{

 private String name;

 private String description;

 private String label;

 private Envelope lonLatEnvelope;

 private List<String> keywords;

 private RectifiedGrid rectifiedGrid;

 private List<String> supportedCRSs;

 private String nativeFormat;

 private List<String> supportedFormats;

 private String defaultInterpolation;

 private List<String> supportedInterpolations;


public void setName(String name){
    this.name = name;
}


public void setSupportedFormats(List<String> supportedFormats){
    this.supportedFormats = supportedFormats;
}


public String getName(){
    return name;
}


public String getLabel(){
    return label;
}


public void setNativeFormat(String nativeFormat){
    this.nativeFormat = nativeFormat;
}


public List<String> getKeywords(){
    return keywords;
}


public String getNativeFormat(){
    return nativeFormat;
}


public void setSupportedCRSs(List<String> supportedCRSs){
    this.supportedCRSs = supportedCRSs;
}


public void setDescription(String description){
    this.description = description;
}


public RectifiedGrid getRectifiedGrid(){
    return rectifiedGrid;
}


public String getDescription(){
    return description;
}


public void setKeywords(List<String> keywords){
    this.keywords = keywords;
}


public List<String> getSupportedFormats(){
    return supportedFormats;
}


public String getDefaultInterpolation(){
    return defaultInterpolation;
}


public void setSupportedInterpolations(List<String> supportedInterpolations){
    this.supportedInterpolations = supportedInterpolations;
}


public void setLabel(String label){
    this.label = label;
}


public void setRectifiedGrid(RectifiedGrid rectifiedGrid){
    this.rectifiedGrid = rectifiedGrid;
}


public void setDefaultInterpolation(String defaultInterpolation){
    this.defaultInterpolation = defaultInterpolation;
}


public List<String> getSupportedCRSs(){
    return supportedCRSs;
}


public void setLonLatEnvelope(Envelope lonLatEnvelope){
    this.lonLatEnvelope = lonLatEnvelope;
}


public Envelope getLonLatEnvelope(){
    return lonLatEnvelope;
}


public List<String> getSupportedInterpolations(){
    return supportedInterpolations;
}


}