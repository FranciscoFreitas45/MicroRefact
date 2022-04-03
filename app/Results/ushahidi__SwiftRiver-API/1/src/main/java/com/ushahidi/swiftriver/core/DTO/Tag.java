package com.ushahidi.swiftriver.core.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
public class Tag implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String hash;

 private  String tag;

 private  String tagCanonical;

 private  String type;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

public Tag() {
}
public String getHash(){
    return hash;
}


public String getType(){
    return type;
}


public String getTagCanonical(){
    return tagCanonical;
}


public long getId(){
    return id;
}


public String getTag(){
    return tag;
}


public void setTag(String tag){
    this.tag = tag;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTag"))

.queryParam("tag",tag)
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


}