package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class GetTagTrend {

 private  String tag;

@JsonProperty("tag_type")
 private  String tagType;

@JsonProperty("occurrences")
 private  long count;

@JsonProperty("trend_date")
 private  String datePublished;


public String getTagType(){
    return tagType;
}


public void setTagType(String tagType){
    this.tagType = tagType;
}


public void setDatePublished(String datePublished){
    this.datePublished = datePublished;
}


public void setTag(String tag){
    this.tag = tag;
}


public String getDatePublished(){
    return datePublished;
}


public String getTag(){
    return tag;
}


public long getCount(){
    return count;
}


public void setCount(long count){
    this.count = count;
}


}