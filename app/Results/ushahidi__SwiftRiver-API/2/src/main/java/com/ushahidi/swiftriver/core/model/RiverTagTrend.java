package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "river_tag_trends")
public class RiverTagTrend {

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "Seq")
@TableGenerator(name = "Seq", table = "seq", pkColumnName = "name", valueColumnName = "id", pkColumnValue = "river_tag_trends")
 private  long id;

 private  String hash;

@ManyToOne
 private  River river;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "date_pub")
 private  Date datePublished;

 private  String tag;

@Column(name = "tag_type")
 private  String tagType;

 private  long count;

@Transient
 private  Float latitude;

@Transient
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


public void setRiver(River river){
    this.river = river;
}


public long getId(){
    return id;
}


public String getTag(){
    return tag;
}


public void setLatitude(Float latitude){
    this.latitude = latitude;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    RiverTagTrend other = (RiverTagTrend) obj;
    if (hash == null) {
        if (other.hash != null)
            return false;
    } else if (!hash.equals(other.hash))
        return false;
    return true;
}


public void setDatePublished(Date datePublished){
    this.datePublished = datePublished;
}


public void setTagType(String tagType){
    this.tagType = tagType;
}


public void setId(long id){
    this.id = id;
}


public void setHash(String hash){
    this.hash = hash;
}


public Date getDatePublished(){
    return datePublished;
}


public void setTag(String tag){
    this.tag = tag;
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