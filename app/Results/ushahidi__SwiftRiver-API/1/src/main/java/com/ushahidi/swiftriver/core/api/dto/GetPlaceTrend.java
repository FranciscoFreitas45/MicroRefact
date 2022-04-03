package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class GetPlaceTrend {

@JsonProperty("place_name")
 private  String tag;

 private  Float latitude;

 private  Float longitude;

@JsonProperty("occurrences")
 private  long count;

@JsonProperty("trend_date")
 private  String datePublished;


public void setLatitude(Float latitude){
    this.latitude = latitude;
}


public Float getLongitude(){
    return longitude;
}


public Float getLatitude(){
    return latitude;
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


public void setLongitude(Float longitude){
    this.longitude = longitude;
}


public long getCount(){
    return count;
}


public void setCount(long count){
    this.count = count;
}


}