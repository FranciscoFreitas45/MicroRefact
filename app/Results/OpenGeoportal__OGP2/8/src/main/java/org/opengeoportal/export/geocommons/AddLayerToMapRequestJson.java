package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddLayerToMapRequestJson {

@JsonProperty("source")
 private String source;

@JsonProperty("visible")
 private Boolean visible;

@JsonProperty("title")
 private String title;


public void setVisible(Boolean visible){
    this.visible = visible;
}


public void setSource(String source){
    this.source = source;
}


public String getTitle(){
    return title;
}


public Boolean getVisible(){
    return visible;
}


public String getSource(){
    return source;
}


public void setTitle(String title){
    this.title = title;
}


}