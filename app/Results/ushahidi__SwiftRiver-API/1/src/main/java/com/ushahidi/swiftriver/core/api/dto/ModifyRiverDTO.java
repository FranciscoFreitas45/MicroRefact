package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class ModifyRiverDTO {

@JsonProperty("name")
 private  String riverName;

 private  String description;

@JsonProperty("public")
 private  Boolean riverPublic;


public String getRiverName(){
    return riverName;
}


public Boolean getRiverPublic(){
    return riverPublic;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setRiverPublic(Boolean riverPublic){
    this.riverPublic = riverPublic;
}


public void setRiverName(String riverName){
    this.riverName = riverName;
}


}