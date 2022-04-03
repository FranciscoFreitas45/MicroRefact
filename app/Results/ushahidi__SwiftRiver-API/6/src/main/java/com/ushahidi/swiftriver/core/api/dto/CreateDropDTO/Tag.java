package com.ushahidi.swiftriver.core.api.dto.CreateDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Tag {

 private  String tag;

 private  String type;


public String getType(){
    return type;
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