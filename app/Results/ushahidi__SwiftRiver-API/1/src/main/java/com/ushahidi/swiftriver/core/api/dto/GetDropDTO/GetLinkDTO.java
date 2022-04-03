package com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetLinkDTO {

 private  long id;

 private  String url;


public String getUrl(){
    return url;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setUrl(String url){
    this.url = url;
}


}