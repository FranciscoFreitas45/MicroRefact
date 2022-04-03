package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "media_thumbnails")
public class MediaThumbnail {

@Id
 private  long id;

@ManyToOne
@JoinColumn(name = "media_id")
 private  Media media;

 private  int size;

 private  String url;


public String getUrl(){
    return url;
}


public int getSize(){
    return size;
}


public Media getMedia(){
    return media;
}


public void setSize(int size){
    this.size = size;
}


public void setId(long id){
    this.id = id;
}


public void setMedia(Media media){
    this.media = media;
}


public long getId(){
    return id;
}


public void setUrl(String url){
    this.url = url;
}


}