package com.ushahidi.swiftriver.core.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Place {

 private  long id;

 private  String hash;

 private  String placeName;

 private  String placeNameCanonical;

 private  Float longitude;

 private  Float latitude;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Place() {
}
public String getPlaceName(){
    return placeName;
}


public String getHash(){
    return hash;
}


public Float getLongitude(){
    return longitude;
}


public String getPlaceNameCanonical(){
    return placeNameCanonical;
}


public Float getLatitude(){
    return latitude;
}


public long getId(){
    return id;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPlaceName"))

.queryParam("placeName",placeName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLatitude(Float latitude){
    this.latitude = latitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLatitude"))

.queryParam("latitude",latitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLongitude(Float longitude){
    this.longitude = longitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLongitude"))

.queryParam("longitude",longitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPlaceNameCanonical(String placeNameCanonical){
    this.placeNameCanonical = placeNameCanonical;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPlaceNameCanonical"))

.queryParam("placeNameCanonical",placeNameCanonical)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHash(String hash){
    this.hash = hash;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setHash"))

.queryParam("hash",hash)
;
restTemplate.put(builder.toUriString(),null);
}


}