package com.ec.survey.model;
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
@Entity
@Table(name = "ARCHIVE", indexes = { @Index(name = "IDX_ARCHIVE", columnList = "ARCHIVE_USER, ARCHIVE_DATE") })
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


@Transient
public String getFormattedArchived(){
    return Tools.formatDate(archived, ConversionTools.DateTimeFormat);
}


public void setSurveyUID(String surveyUID){
    this.surveyUID = surveyUID;
}


@Column(name = "ARCHIVE_SLANGS")
public String getLanguages(){
    return languages;
}


public void setRestoring(Boolean restoring){
    this.restoring = restoring;
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


public void setOwner(String owner){
    this.owner = owner;
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


public void setLanguages(String languages){
    this.languages = languages;
}


@Column(name = "ARCHIVE_SREPLIES")
public int getReplies(){
    return replies;
}


public void setId(Integer id){
    this.id = id;
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


public void setFinished(boolean finished){
    this.finished = finished;
}


@Column(name = "ARCHIVE_STITLE")
public String getSurveyTitle(){
    return surveyTitle;
}


public void setArchived(Date archived){
    this.archived = archived;
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
@Column(name = "ARCHIVE_CREATED")
public Date getCreated(){
    return created;
}


public void setCreated(Date created){
    this.created = created;
}


@Column(name = "ARCHIVE_ERROR")
public String getError(){
    return error;
}


public void setSurveyTitle(String surveyTitle){
    this.surveyTitle = surveyTitle;
}


public void setError(String error){
    this.error = error;
}


public void setSurveyHasUploadedFiles(Boolean surveyHasUploadedFiles){
    this.surveyHasUploadedFiles = surveyHasUploadedFiles;
}


@Column(name = "ARCHIVE_RESTORE")
public Boolean isRestoring(){
    return restoring;
}


public void setReplies(Integer replies){
    this.replies = replies != null ? replies : 0;
}


@Column(name = "ARCHIVE_USER")
public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}