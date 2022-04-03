package com.ushahidi.swiftriver.core.api.dto;
 public class ModifyChannelDTO {

 private  String channel;

 private  Boolean active;

 private  String parameters;


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setActive(Boolean active){
    this.active = active;
}


public String getChannel(){
    return channel;
}


public String getParameters(){
    return parameters;
}


public Boolean getActive(){
    return active;
}


public void setChannel(String channel){
    this.channel = channel;
}


}