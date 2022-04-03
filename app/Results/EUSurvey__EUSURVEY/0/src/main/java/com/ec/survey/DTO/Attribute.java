package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class Attribute {

 private  Integer id;

 private  Integer attendeeId;

 private  AttributeName attributeName;

 private  String value;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Attribute() {
}public Attribute(AttributeName attributeName, String value) {
    this.attributeName = attributeName;
    this.value = value;
}
@Column(name = "ATTE_ID")
public Integer getAttendeeId(){
    return attendeeId;
}


@Lob
@Column(name = "ATTRIBUTE_VALUE")
public String getValue(){
    return value;
}


@Id
@Column(name = "ATTRIBUTE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@ManyToOne(optional = false)
public AttributeName getAttributeName(){
    return attributeName;
}


public void setAttributeName(AttributeName attributeName){
    this.attributeName = attributeName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAttributeName"))

.queryParam("attributeName",attributeName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setValue(String value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}