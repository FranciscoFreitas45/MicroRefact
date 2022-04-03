package com.ec.survey.model;
 import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
import java.util.Date;
import javax.persistence;
@Entity
@Table(name = "SURABUSE", indexes = { @Index(name = "IDX_SURABUSE", columnList = "SURABUSE_SURVEY, SURABUSE_DATE") })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Abuse {

 private  int id;

 private  String surveyUid;

 private  String type;

 private  String text;

 private  String email;

 private  Date created;

 protected  Logger logger;

public Abuse(String surveyUid, String type, String text, String email) {
    this.surveyUid = surveyUid;
    this.type = type;
    this.text = text;
    this.email = email;
    this.created = new Date();
}
public void setEmail(String email){
    this.email = email;
}


@Column(name = "SURABUSE_TYPE")
public String getType(){
    return type;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


@Column(name = "SURABUSE_TEXT")
public String getText(){
    return text;
}


public void setId(Integer id){
    this.id = id;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
@Column(name = "SURABUSE_DATE")
public Date getCreated(){
    return created;
}


@Column(name = "SURABUSE_EMAIL")
public String getEmail(){
    return email;
}


@Id
@Column(name = "SURABUSE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setCreated(Date created){
    this.created = created;
}


public void setType(String type){
    this.type = type;
}


@Column(name = "SURABUSE_SURVEY")
public String getSurveyUid(){
    return surveyUid;
}


public void setText(String text){
    this.text = text;
}


}