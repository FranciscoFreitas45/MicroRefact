package com.ushahidi.swiftriver.core.api.dto;
 public class CreatePlaceDTO {

 private  String name;

 private  float latitude;

 private  float longitude;


public void setName(String name){
    this.name = name;
}


public void setLatitude(float latitude){
    this.latitude = latitude;
}


public String getName(){
    return name;
}


public float getLongitude(){
    return longitude;
}


public float getLatitude(){
    return latitude;
}


public void setLongitude(float longitude){
    this.longitude = longitude;
}


}