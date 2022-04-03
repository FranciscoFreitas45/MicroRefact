package com.sobey.cmop.mvc.entity.ToJson;
 public class MdnVodJson {

 private  Integer id;

 private  String vodDomain;

 private  String vodProtocol;

 private  String sourceStreamerUrl;

 private  String sourceOutBandwidth;


public void setSourceStreamerUrl(String sourceStreamerUrl){
    this.sourceStreamerUrl = sourceStreamerUrl;
}


public void setSourceOutBandwidth(String sourceOutBandwidth){
    this.sourceOutBandwidth = sourceOutBandwidth;
}


public String getVodProtocol(){
    return vodProtocol;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public String getVodDomain(){
    return vodDomain;
}


public void setVodDomain(String vodDomain){
    this.vodDomain = vodDomain;
}


public String getSourceStreamerUrl(){
    return sourceStreamerUrl;
}


public String getSourceOutBandwidth(){
    return sourceOutBandwidth;
}


public void setVodProtocol(String vodProtocol){
    this.vodProtocol = vodProtocol;
}


}