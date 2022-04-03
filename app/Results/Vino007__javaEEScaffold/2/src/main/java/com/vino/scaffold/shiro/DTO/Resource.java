package com.vino.scaffold.shiro.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.vino.scaffold.entity.base.BaseEntity;
public class Resource extends BaseEntity<Long>{

 private  String name;

 private  String type;

 private  Integer priority;

 private  Long parentId;

 private  String permission;

 private  Boolean available;

 private  String url;

 private  Set<Role> roles;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

public Resource() {
}public Resource(String name, String type, String permission) {
    this.name = name;
    this.type = type;
    this.permission = permission;
}
public String getName(){
    return name;
}


public String getPermission(){
    return permission;
}


public String getUrl(){
    return url;
}


public String getType(){
    return type;
}


public Integer getPriority(){
    return priority;
}


public Boolean getAvailable(){
    return available;
}


public Long getParentId(){
    return parentId;
}


public Set<Role> getRoles(){
    return roles;
}


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParentId(Long parentId){
    this.parentId = parentId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParentId"))

.queryParam("parentId",parentId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUrl(String url){
    this.url = url;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUrl"))

.queryParam("url",url)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPriority(Integer priority){
    this.priority = priority;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPriority"))

.queryParam("priority",priority)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAvailable(Boolean available){
    this.available = available;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAvailable"))

.queryParam("available",available)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPermission(String permission){
    this.permission = permission;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPermission"))

.queryParam("permission",permission)
;
restTemplate.put(builder.toUriString(),null);
}


}