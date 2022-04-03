package com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetMediaDTO {

 private  long id;

 private  String url;

 private  String type;

 private  List<MediaThumbnail> thumbnails;

 private  int size;

 private  String url;


public String getUrl(){
    return url;
}


public List<MediaThumbnail> getThumbnails(){
    return thumbnails;
}


public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public String getType(){
    return type;
}


public void setId(long id){
    this.id = id;
}


public void setThumbnails(List<MediaThumbnail> thumbnails){
    this.thumbnails = thumbnails;
}


public long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}