package com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Bucket {

@JsonProperty("id")
 private  long id;

 private  String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


}