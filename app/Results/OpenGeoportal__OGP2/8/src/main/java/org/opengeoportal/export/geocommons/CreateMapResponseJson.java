package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMapResponseJson {

@JsonProperty("id")
 private String id;


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}