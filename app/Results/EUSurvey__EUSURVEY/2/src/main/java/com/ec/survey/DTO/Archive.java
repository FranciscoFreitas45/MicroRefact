package com.ec.survey.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.springframework.format.annotation.DateTimeFormat;
public class Archive {

 private  long serialVersionUID;

 private  Integer id;

 private  Date archived;

 private  Date created;

 private  String surveyUID;

 private  String surveyTitle;

 private  String surveyShortname;

 private  String owner;

 private  int userId;

 private  String languages;

 private  String error;

 private  boolean finished;

 private  Boolean restoring;

 private  int replies;

 private  Boolean surveyHasUploadedFiles;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Transient
public String getFormattedArchived(){
    return Tools.formatDate(archived, ConversionTools.DateTimeFormat);
}


@Column(name = "ARCHIVE_SLANGS")
public String getLanguages(){
    return languages;
}


@Id
@Column(name = "ARCHIVE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ARCHIVE_SOWNER")
public String getOwner(){
    return owner;
}


@Column(name = "ARCHIVE_SSHORTNAME")
public String getSurveyShortname(){
    return surveyShortname;
}


@Column(name = "ARCHIVE_SUPLOADEDFILES")
public Boolean getSurveyHasUploadedFiles(){
    return surveyHasUploadedFiles;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
@Column(name = "ARCHIVE_DATE")
public Date getArchived(){
    return archived;
}


@Column(name = "ARCHIVE_SREPLIES")
public int getReplies(){
    return replies;
}


@Transient
public String getFormattedCreated(){
    return Tools.formatDate(created, ConversionTools.DateTimeFormat);
}


@Column(name = "ARCHIVE_FINISHED")
public boolean getFinished(){
    return finished;
}


@Column(name = "ARCHIVE_SUID")
public String getSurveyUID(){
    return surveyUID;
}


@Column(name = "ARCHIVE_STITLE")
public String getSurveyTitle(){
    return surveyTitle;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
@Column(name = "ARCHIVE_CREATED")
public Date getCreated(){
    return created;
}


@Column(name = "ARCHIVE_ERROR")
public String getError(){
    return error;
}


@Column(name = "ARCHIVE_USER")
public int getUserId(){
    return userId;
}


public void setArchived(Date archived){
    this.archived = archived;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setArchived"))

.queryParam("archived",archived)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreated(Date created){
    this.created = created;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreated"))

.queryParam("created",created)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyTitle(String surveyTitle){
    this.surveyTitle = surveyTitle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyTitle"))

.queryParam("surveyTitle",surveyTitle)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyUID(String surveyUID){
    this.surveyUID = surveyUID;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyUID"))

.queryParam("surveyUID",surveyUID)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReplies(Integer replies){
    this.replies = replies != null ? replies : 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReplies"))

.queryParam("replies",replies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyShortname"))

.queryParam("surveyShortname",surveyShortname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOwner(String owner){
    this.owner = owner;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOwner"))

.queryParam("owner",owner)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(int userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguages(String languages){
    this.languages = languages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguages"))

.queryParam("languages",languages)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyHasUploadedFiles(Boolean surveyHasUploadedFiles){
    this.surveyHasUploadedFiles = surveyHasUploadedFiles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyHasUploadedFiles"))

.queryParam("surveyHasUploadedFiles",surveyHasUploadedFiles)
;
restTemplate.put(builder.toUriString(),null);
}


}