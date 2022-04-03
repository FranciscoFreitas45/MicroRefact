package com.app.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
public class Schedule {

 private  Integer id;

 private  Division division;

 private  String string;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public Schedule() {
}public Schedule(Division division, String string) {
    this.division = division;
    this.string = string;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "division", unique = true)
public Division getDivision(){
    return this.division;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "string", length = 1000)
public String getString(){
    return this.string;
}


public void setString(String string){
    this.string = string;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setString"))

.queryParam("string",string)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDivision(Division division){
    this.division = division;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDivision"))

.queryParam("division",division)
;
restTemplate.put(builder.toUriString(),null);
}


}