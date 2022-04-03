package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class AddWMSLayerToMapRequestJson extends AddLayerToMapRequestJson{

@JsonProperty("visibleLayers")
 private String[] visibleLayers;


public void setVisibleLayers(String[] visibleLayers){
    this.visibleLayers = visibleLayers;
}


public String[] getVisibleLayers(){
    return visibleLayers;
}


}