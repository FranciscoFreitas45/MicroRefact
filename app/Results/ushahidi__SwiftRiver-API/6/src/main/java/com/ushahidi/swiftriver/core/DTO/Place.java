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


}