package org.opengeoportal.utilities;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkShortenReturnGoogle {

@JsonProperty
 private  String kind;

@JsonProperty
 private  String id;

@JsonProperty
 private  String longUrl;


public void setLongUrl(String longUrl){
    this.longUrl = longUrl;
}


public void setId(String id){
    this.id = id;
}


public void setKind(String kind){
    this.kind = kind;
}


public String getId(){
    return this.id;
}


public String getKind(){
    return this.kind;
}


public String getLongUrl(){
    return this.longUrl;
}


}