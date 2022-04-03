package com.ushahidi.swiftriver.core.DTO;
 import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class MediaThumbnail {

 private  long id;

 private  Media media;

 private  int size;

 private  String url;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getUrl(){
    return url;
}


public int getSize(){
    return size;
}


public Media getMedia(){
    return media;
}


public long getId(){
    return id;
}


public void setMedia(Media media){
    this.media = media;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMedia"))

.queryParam("media",media)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSize(int size){
    this.size = size;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSize"))

.queryParam("size",size)
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


}