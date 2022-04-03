package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import javax.persistence;
public class Translation implements Comparable{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer surveyId;

 private  Translations translations;

 private  String key;

 private  String label;

 private  String language;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public Translation() {
}public Translation(String key, String label, String language, Integer surveyId, Translations translations) {
    this.key = key;
    this.label = label;
    this.language = language;
    this.surveyId = surveyId;
    this.translations = translations;
}
@Column(name = "TRANSLATIONKEY")
public String getKey(){
    return key;
}


@Column(name = "LANGUAGE")
public String getLanguage(){
    return language;
}


@Lob
@Column(name = "LABEL", nullable = false)
public String getLabel(){
    return label;
}


@Id
@Column(name = "TRANSLATION_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "SURVEY_ID")
public Integer getSurveyId(){
    return surveyId;
}


@ManyToOne
@JoinColumn(name = "TRANS_ID")
public Translations getTranslations(){
    return translations;
}


public void setLabel(String label){
    this.label = label;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLabel"))

.queryParam("label",label)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyId(Integer surveyId){
    this.surveyId = surveyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyId"))

.queryParam("surveyId",surveyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setKey(String key){
    this.key = key;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setKey"))

.queryParam("key",key)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguage(String language){
    this.language = language;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguage"))

.queryParam("language",language)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTranslations(Translations s){
    this.translations = s;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTranslations"))

.queryParam("s",s)
;
restTemplate.put(builder.toUriString(),null);
}


}