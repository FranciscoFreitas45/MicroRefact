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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


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


public Date getDatePublished(){
    return datePublished;
}


public long getCount(){
    return count;
}


public void setCount(long count){
    this.count = count;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCount"))

.queryParam("count",count)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRiver(River river){
    this.river = river;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRiver"))

.queryParam("river",river)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDatePublished(Date datePublished){
    this.datePublished = datePublished;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDatePublished"))

.queryParam("datePublished",datePublished)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTag(String tag){
    this.tag = tag;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTag"))

.queryParam("tag",tag)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTagType(String tagType){
    this.tagType = tagType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTagType"))

.queryParam("tagType",tagType)
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