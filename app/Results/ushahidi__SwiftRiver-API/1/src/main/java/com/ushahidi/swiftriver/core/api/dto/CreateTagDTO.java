package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateTagDTO {

 private  String tag;

@JsonProperty("tag_type")
 private  String tagType;


public String getTagType(){
    return tagType;
}


public void setTagType(String tagType){
    this.tagType = tagType;
}


public void setTag(String tag){
    this.tag = tag;
}


public String getTag(){
    return tag;
}


}