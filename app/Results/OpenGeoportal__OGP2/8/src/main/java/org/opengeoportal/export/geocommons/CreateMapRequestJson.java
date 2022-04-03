package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class CreateMapRequestJson {

@JsonProperty("title")
 private String title;

@JsonProperty("basemap")
 private String basemap;

@JsonProperty("description")
 private String description;

@JsonProperty("tags")
 private String tags;

@JsonProperty("extent")
 private String[] extent;


public String[] getExtent(){
    return extent;
}


public void setBasemap(String basemap){
    this.basemap = basemap;
}


public String getTitle(){
    return title;
}


public void setTitle(String title){
    this.title = title;
}


public String getTags(){
    return tags;
}


public void setDescription(String description){
    this.description = description;
}


public void setTags(String tags){
    this.tags = tags;
}


public void setExtent(String[] extent){
    this.extent = extent;
}


public String getDescription(){
    return description;
}


public String getBasemap(){
    return basemap;
}


}