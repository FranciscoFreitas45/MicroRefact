package com.sobey.cmop.mvc.entity.ToJson;
 import java.util.List;
public class MdnJson {

 private  Integer id;

 private  String identifier;

 private  String coverArea;

 private  String coverIsp;

 private  String bandwidth;

 private  List<MdnVodJson> mdnVodJsons;

 private  List<MdnLiveJson> mdnLiveJsons;


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public String getCoverIsp(){
    return coverIsp;
}


public Integer getId(){
    return id;
}


public String getCoverArea(){
    return coverArea;
}


public void setCoverIsp(String coverIsp){
    this.coverIsp = coverIsp;
}


public void setMdnVodJsons(List<MdnVodJson> mdnVodJsons){
    this.mdnVodJsons = mdnVodJsons;
}


public List<MdnVodJson> getMdnVodJsons(){
    return mdnVodJsons;
}


public String getIdentifier(){
    return identifier;
}


public List<MdnLiveJson> getMdnLiveJsons(){
    return mdnLiveJsons;
}


public void setId(Integer id){
    this.id = id;
}


public String getBandwidth(){
    return bandwidth;
}


public void setBandwidth(String bandwidth){
    this.bandwidth = bandwidth;
}


public void setCoverArea(String coverArea){
    this.coverArea = coverArea;
}


public void setMdnLiveJsons(List<MdnLiveJson> mdnLiveJsons){
    this.mdnLiveJsons = mdnLiveJsons;
}


}