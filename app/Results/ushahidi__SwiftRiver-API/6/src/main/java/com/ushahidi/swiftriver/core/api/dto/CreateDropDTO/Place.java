package com.ushahidi.swiftriver.core.api.dto.CreateDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Place {

 private  String type;

@JsonProperty("name")
 private  String placeName;

 private  float longitude;

 private  float latitude;


public void setLatitude(float latitude){
    this.latitude = latitude;
}


public String getPlaceName(){
    return placeName;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
}


public float getLongitude(){
    return longitude;
}


public String getType(){
    return type;
}


public float getLatitude(){
    return latitude;
}


public void setType(String type){
    this.type = type;
}


public void setLongitude(float longitude){
    this.longitude = longitude;
}


}