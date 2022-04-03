package com.lingxiang2014.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class Sn extends BaseEntity{

 private  long serialVersionUID;

 private  Type type;

 private  Long lastValue;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Column(nullable = false)
public Long getLastValue(){
    return lastValue;
}


@Column(nullable = false, updatable = false, unique = true)
public Type getType(){
    return type;
}


public void setLastValue(Long lastValue){
    this.lastValue = lastValue;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLastValue"))

.queryParam("lastValue",lastValue)
;
restTemplate.put(builder.toUriString(),null);
}


}