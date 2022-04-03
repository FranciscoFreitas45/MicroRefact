package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class ChannelUpdateNotification {

 private  Long id;

@JsonProperty("river_id")
 private  long riverId;

 private  String channel;

 private  String parameters;


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setRiverId(long riverId){
    this.riverId = riverId;
}


public String getChannel(){
    return channel;
}


public String getParameters(){
    return parameters;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public long getRiverId(){
    return riverId;
}


}