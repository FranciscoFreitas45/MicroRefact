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

public Place() {
}
public String getPlaceName(){
    return placeName;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
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


public void setPlaceNameCanonical(String placeNameCanonical){
    this.placeNameCanonical = placeNameCanonical;
}


public void setLatitude(Float latitude){
    this.latitude = latitude;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(placeName).append(longitude).append(latitude).toHashCode();
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    Place other = (Place) obj;
    return new EqualsBuilder().append(placeName, other.placeName).append(longitude, other.longitude).append(latitude, other.latitude).isEquals();
}


public void setId(long id){
    this.id = id;
}


public void setHash(String hash){
    this.hash = hash;
}


public void setLongitude(Float longitude){
    this.longitude = longitude;
}


}