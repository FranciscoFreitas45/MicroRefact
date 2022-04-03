package com.ushahidi.swiftriver.core.api.dto;
 public class CreateChannelDTO {

 private  String channel;

 private  String parameters;


public void setParameters(String parameters){
    this.parameters = parameters;
}


public String getChannel(){
    return channel;
}


public String getParameters(){
    return parameters;
}


public void setChannel(String channel){
    this.channel = channel;
}


}