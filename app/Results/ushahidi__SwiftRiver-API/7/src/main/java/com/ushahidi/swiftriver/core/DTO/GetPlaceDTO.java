package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetPlaceDTO {

 private  long id;

 private  String type;

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


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setLongitude(float longitude){
    this.longitude = longitude;
}


}