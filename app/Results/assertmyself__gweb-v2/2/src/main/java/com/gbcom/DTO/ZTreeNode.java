package com.gbcom.DTO;
 import java.util.ArrayList;
import java.util.List;
public class ZTreeNode extends Node{

 private  String id;

 private  String name;

 private  String text;

 private  Boolean check;

 private  Boolean open;

 private  Boolean isParent;

 private  Boolean isLeaf;

 private  String uid;

 private  String type;

 private  String icon;

 private  Boolean extendLeaf;

 private  List<ZTreeNode> children;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public Boolean getIsLeaf(){
    return isLeaf;
}


public String getName(){
    return name;
}


public Boolean getOpen(){
    return open;
}


public String getText(){
    return text;
}


public Boolean getExtendLeaf(){
    return extendLeaf;
}


public String getId(){
    return id;
}


public String getIcon(){
    return icon;
}


public List<ZTreeNode> getChildren(){
    return children;
}


public String getUid(){
    return uid;
}


public Boolean getCheck(){
    return check;
}


public String getType(){
    return type;
}


public Boolean getIsParent(){
    return isParent;
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsLeaf(Boolean isLeaf){
    this.isLeaf = isLeaf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsLeaf"))

.queryParam("isLeaf",isLeaf)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIcon(String icon){
    this.icon = icon;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIcon"))

.queryParam("icon",icon)
;
restTemplate.put(builder.toUriString(),null);
}


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


}