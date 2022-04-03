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


}