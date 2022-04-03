package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateBucketDTO {

 private  String name;

@JsonProperty("public")
 private  Boolean published;

@JsonProperty("default_layout")
 private  String defaultLayout;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getDefaultLayout(){
    return defaultLayout;
}


public Boolean isPublished(){
    return published;
}


public void setPublished(Boolean published){
    this.published = published;
}


public void setDefaultLayout(String defaultLayout){
    this.defaultLayout = defaultLayout;
}


}