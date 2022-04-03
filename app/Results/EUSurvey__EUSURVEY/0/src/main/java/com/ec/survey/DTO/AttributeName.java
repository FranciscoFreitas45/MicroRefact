package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class AttributeName {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer ownerId;

 private  String name;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public AttributeName() {
}public AttributeName(Integer ownerId, String name) {
    this.name = name;
    this.ownerId = ownerId;
}
@Column(name = "AN_NAME")
public String getName(){
    return name;
}


@Column(name = "OWNER_ID")
public Integer getOwnerId(){
    return ownerId;
}


@Id
@Column(name = "AN_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOwnerId(Integer ownerId){
    this.ownerId = ownerId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOwnerId"))

.queryParam("ownerId",ownerId)
;
restTemplate.put(builder.toUriString(),null);
}


}