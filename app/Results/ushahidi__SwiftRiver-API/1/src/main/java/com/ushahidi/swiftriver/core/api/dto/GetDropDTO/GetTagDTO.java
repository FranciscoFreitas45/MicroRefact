package com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetTagDTO {

 private  long id;

 private  String tag;

 private  String type;


public String getType(){
    return type;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setTag(String tag){
    this.tag = tag;
}


public String getTag(){
    return tag;
}


public void setType(String type){
    this.type = type;
}


}