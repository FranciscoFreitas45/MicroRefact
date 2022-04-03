package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
public class RiverTagTrend {

 private  long id;

 private  String hash;

 private  River river;

 private  Date datePublished;

 private  String tag;

 private  String tagType;

 private  long count;

 private  Float latitude;

 private  Float longitude;


public River getRiver(){
    return river;
}


public String getHash(){
    return hash;
}


public Float getLongitude(){
    return longitude;
}


public String getTagType(){
    return tagType;
}


public Float getLatitude(){
    return latitude;
}


public long getId(){
    return id;
}


public String getTag(){
    return tag;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
}


public void setDatePublished(Date datePublished){
    this.datePublished = datePublished;
}


public void setId(long id){
    this.id = id;
}


public Date getDatePublished(){
    return datePublished;
}


public void setLongitude(Float longitude){
    this.longitude = longitude;
}


public long getCount(){
    return count;
}


}