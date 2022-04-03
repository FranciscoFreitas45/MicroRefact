package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class Publication {

 private  long serialVersionUID;

 private  Integer id;

 private  boolean showContent;

 private  boolean showStatistics;

 private  boolean showCharts;

 private  boolean showSearch;

 private  boolean allQuestions;

 private  boolean allContributions;

 private  boolean showUploadedDocuments;

 private  ResultFilter filter;

 private  String password;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Id
@Column(name = "PUB_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "PUB_UPLOADED")
public Boolean getShowUploadedDocuments(){
    return showUploadedDocuments;
}


@OneToOne(cascade = CascadeType.ALL)
public ResultFilter getFilter(){
    return filter;
}


@Column(name = "PUB_PASSWORD")
public String getPassword(){
    return password;
}


@Column(name = "PUB_ALLQ")
public boolean isAllQuestions(){
    return allQuestions;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAllQuestions"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


@Transient
public boolean isSelected(String questionId){
    return filter != null && filter.getVisibleQuestions().contains(questionId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSelected"))

.queryParam("questionId",questionId)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}