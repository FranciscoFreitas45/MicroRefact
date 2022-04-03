package com.ushahidi.swiftriver.core.api.dto.CreateDropDTO.Media;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class MediaThumbnail {

 private  int size;

 private  String url;


public String getUrl(){
    return url;
}


public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public void setUrl(String url){
    this.url = url;
}


}