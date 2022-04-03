package org.sdrc.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class CounterCount implements Serializable{

 private Integer id;

 private Integer noOfCounter;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Integer getNoOfCounter(){
    return noOfCounter;
}


public Integer getId(){
    return id;
}


public void setNoOfCounter(Integer noOfCounter){
    this.noOfCounter = noOfCounter;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoOfCounter"))

.queryParam("noOfCounter",noOfCounter)
;
restTemplate.put(builder.toUriString(),null);
}


}