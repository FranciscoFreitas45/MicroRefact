package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
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
@Column(name = "LANGUAGE_NAME")
public String getName(){
    return name;
}


@Column(name = "LANGUAGE_ENNAME")
public String getEnglishName(){
    return englishName;
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


}