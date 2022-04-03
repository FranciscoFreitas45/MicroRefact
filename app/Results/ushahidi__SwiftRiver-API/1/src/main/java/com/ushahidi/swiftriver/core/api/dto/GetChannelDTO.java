package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class GetChannelDTO {

 private  long id;

 private  String channel;

 private  boolean active;

 private  String parameters;

@JsonProperty("drop_count")
 private  int dropCount;


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setActive(boolean active){
    this.active = active;
}


public String getChannel(){
    return channel;
}


public String getParameters(){
    return parameters;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public boolean isActive(){
    return active;
}


}