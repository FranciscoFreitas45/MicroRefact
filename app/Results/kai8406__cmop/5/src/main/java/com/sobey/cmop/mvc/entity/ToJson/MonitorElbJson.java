package com.sobey.cmop.mvc.entity.ToJson;
 public class MonitorElbJson {

 private  Integer id;

 private  String networkElbItem;

 private  String identifier;


public String getNetworkElbItem(){
    return networkElbItem;
}


public String getIdentifier(){
    return identifier;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setNetworkElbItem(String networkElbItem){
    this.networkElbItem = networkElbItem;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}