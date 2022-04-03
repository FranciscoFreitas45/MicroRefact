package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class Draft {

 private  long serialVersionUID;

 private  Integer id;

 private  AnswerSet answerSet;

 private  String uniqueId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@OneToOne(cascade = CascadeType.ALL)
public AnswerSet getAnswerSet(){
    return answerSet;
}


@Id
@Column(name = "DRAFT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "DRAFT_UID")
public String getUniqueId(){
    return uniqueId;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUniqueId"))

.queryParam("uniqueId",uniqueId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAnswerSet(AnswerSet s){
    this.answerSet = s;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAnswerSet"))

.queryParam("s",s)
;
restTemplate.put(builder.toUriString(),null);
}


}