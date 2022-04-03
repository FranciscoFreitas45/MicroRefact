package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Media {

 private  long id;

 private  String hash;

 private  String url;

 private  String type;

 private  List<MediaThumbnail> thumbnails;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Media() {
}
public String getUrl(){
    return url;
}


public List<MediaThumbnail> getThumbnails(){
    return thumbnails;
}


public String getHash(){
    return hash;
}


public String getType(){
    return type;
}


public long getId(){
    return id;
}


public void setId(long id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUrl(String url){
    this.url = url;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUrl"))

.queryParam("url",url)
;
restTemplate.put(builder.toUriString(),null);
}


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setThumbnails(List<MediaThumbnail> thumbnails){
    this.thumbnails = thumbnails;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setThumbnails"))

.queryParam("thumbnails",thumbnails)
;
restTemplate.put(builder.toUriString(),null);
}


}