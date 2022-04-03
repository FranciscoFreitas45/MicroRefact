package com.ec.survey.model;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.cache.annotation.Cacheable;
import javax.persistence;
import java.util;
@Entity
@Table(name = "TRANSLATIONS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Translations {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer surveyId;

 private  String surveyUid;

 private  Language language;

 private  boolean active;

 private  boolean complete;

 private  boolean requested;

 private  List<Translation> translations;

 private  String title;

public Translations() {
}
public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


@Id
@Column(name = "TRANSLATIONS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setTranslations(List<Translation> translations){
    this.translations = translations;
}


public boolean removeTranslationByKey(String key){
    Translation found = null;
    for (Translation translation : translations) {
        if (translation.getKey().equals(key)) {
            found = translation;
            break;
        }
    }
    if (found != null) {
        translations.remove(found);
        return true;
    }
    return false;
}


@Transient
public Collection<Translation> getFilteredTranslations(){
    Map<String, Translation> map = new HashMap<>();
    for (Translation t : translations) {
        map.put(t.getKey(), t);
    }
    return map.values();
}


@Transient
public String getTitle(){
    return title;
}


@Transient
public Map<String,Translation> getTranslationsByKey(){
    HashMap<String, Translation> result = new HashMap<>();
    for (Translation translation : translations) {
        result.put(translation.getKey(), translation);
    }
    return result;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "SURVEY_UID")
public String getSurveyUid(){
    return surveyUid;
}


@ManyToOne
@JoinColumn(name = "LANGUAGE", nullable = false)
public Language getLanguage(){
    return language;
}


@Transient
public Map<String,String> getInfo(){
    Map<String, String> result = new HashMap<>();
    for (Translation translation : translations) {
        result.put(translation.getKey(), translation.getLabel());
    }
    return result;
}


public void setTitle(String title){
    this.title = title;
}


public void setComplete(Boolean complete){
    if (complete != null) {
        this.complete = complete;
    }
}


@Transient
public Translation getTranslationById(String translationId){
    if (translationId == null || translationId.trim().length() == 0)
        return null;
    for (Translation translation : translations) {
        if (translation.getId().toString().equals(translationId)) {
            return translation;
        }
    }
    return null;
}


@Column(name = "COMPLETE")
public Boolean getComplete(){
    return complete;
}


public void setRequested(Boolean requested){
    if (requested != null) {
        this.requested = requested;
    }
}


@Column(name = "REQUESTED")
public Boolean getRequested(){
    return requested;
}


@Transient
public Map<String,String> getTranslationsMap(){
    HashMap<String, String> result = new HashMap<>();
    for (Translation translation : translations) {
        result.put(translation.getKey(), translation.getLabel());
    }
    return result;
}


public void setActive(Boolean active){
    this.active = active;
}


@Transient
public Set<Translation> getTranslationsOrderedByKey(){
    SortedSet<Translation> result = new TreeSet<>(new TranslationByKeyComparator());
    result.addAll(translations);
    return result;
}


@Column(name = "SURVEY_ID")
public Integer getSurveyId(){
    return surveyId;
}


@Column(name = "SURVEY_ACTIVE")
public Boolean getActive(){
    return active;
}


public void setSurveyId(Integer surveyId){
    this.surveyId = surveyId;
}


@OneToMany(targetEntity = Translation.class, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "translations")
@Fetch(value = FetchMode.SELECT)
@OrderBy("id")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Translation> getTranslations(){
    return translations;
}


public void setLanguage(Language language){
    this.language = language;
}


}