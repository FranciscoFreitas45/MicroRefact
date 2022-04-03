package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateRiverDTO {

@JsonProperty("name")
 private  String riverName;

 private  String description;

@JsonProperty("public")
 private  boolean riverPublic;


public String getRiverName(){
    return riverName;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public boolean isRiverPublic(){
    return riverPublic;
}


public void setRiverPublic(boolean riverPublic){
    this.riverPublic = riverPublic;
}


public void setRiverName(String riverName){
    this.riverName = riverName;
}


}