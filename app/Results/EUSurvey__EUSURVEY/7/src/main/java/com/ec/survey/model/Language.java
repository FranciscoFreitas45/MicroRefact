package com.ec.survey.model;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
@Entity
@Table(name = "LANGUAGES")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Language {

 private  long serialVersionUID;

 private  Integer id;

 private  String code;

 private  String name;

 private  String englishName;

 private  boolean official;

public Language() {
}public Language(String code, String name, String englishName, boolean official) {
    this.code = code;
    this.name = name;
    this.englishName = englishName;
    this.official = official;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "LANGUAGE_NAME")
public String getName(){
    return name;
}


@Column(name = "LANGUAGE_ENNAME")
public String getEnglishName(){
    return englishName;
}


public void setCode(String code){
    this.code = code;
}


public void setOfficial(boolean official){
    this.official = official;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@Column(name = "LANGUAGE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "LANGUAGE_CODE")
public String getCode(){
    return code;
}


public void setEnglishName(String englishName){
    this.englishName = englishName;
}


@Column(name = "LANGUAGE_OFFI")
public boolean isOfficial(){
    return official;
}


}