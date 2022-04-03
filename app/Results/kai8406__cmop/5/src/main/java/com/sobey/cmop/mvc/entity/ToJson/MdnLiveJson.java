package com.sobey.cmop.mvc.entity.ToJson;
 public class MdnLiveJson {

 private  Integer id;

 private  String liveDomain;

 private  String liveProtocol;

 private  String name;

 private  String guid;

 private  String bandwidth;

 private  String streamOutMode;

 private  String encoderMode;

 private  String httpUrl;

 private  String httpBitrate;

 private  String hlsUrl;

 private  String hlsBitrate;


public String getLiveDomain(){
    return liveDomain;
}


public void setName(String name){
    this.name = name;
}


public String getGuid(){
    return guid;
}


public String getHlsUrl(){
    return hlsUrl;
}


public void setGuid(String guid){
    this.guid = guid;
}


public void setHlsBitrate(String hlsBitrate){
    this.hlsBitrate = hlsBitrate;
}


public String getName(){
    return name;
}


public String getHttpBitrate(){
    return httpBitrate;
}


public void setStreamOutMode(String streamOutMode){
    this.streamOutMode = streamOutMode;
}


public String getEncoderMode(){
    return encoderMode;
}


public String getHlsBitrate(){
    return hlsBitrate;
}


public void setHlsUrl(String hlsUrl){
    this.hlsUrl = hlsUrl;
}


public Integer getId(){
    return id;
}


public String getLiveProtocol(){
    return liveProtocol;
}


public void setHttpUrl(String httpUrl){
    this.httpUrl = httpUrl;
}


public void setEncoderMode(String encoderMode){
    this.encoderMode = encoderMode;
}


public String getStreamOutMode(){
    return streamOutMode;
}


public String getHttpUrl(){
    return httpUrl;
}


public void setHttpBitrate(String httpBitrate){
    this.httpBitrate = httpBitrate;
}


public void setId(Integer id){
    this.id = id;
}


public void setLiveDomain(String liveDomain){
    this.liveDomain = liveDomain;
}


public String getBandwidth(){
    return bandwidth;
}


public void setBandwidth(String bandwidth){
    this.bandwidth = bandwidth;
}


public void setLiveProtocol(String liveProtocol){
    this.liveProtocol = liveProtocol;
}


}