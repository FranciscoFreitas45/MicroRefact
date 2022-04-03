package com.ec.survey.model;
 import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.model.administration.User;
import com.ec.survey.Interface.User;
public class SurveyFilter implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  User user;

 private  String shortname;

 private  String title;

 private  String uid;

 private  String owner;

 private  Date generatedFrom;

 private  Date generatedTo;

 private  Date publishedFrom;

 private  Date publishedTo;

 private  Date firstPublishedFrom;

 private  Date firstPublishedTo;

 private  Date startFrom;

 private  Date startTo;

 private  Date endFrom;

 private  Date endTo;

 private  String access;

 private  String status;

 private  String selector;

 private  String keywords;

 private  String[] languages;

 private  String sortKey;

 private  String sortOrder;

 private  String surveys;

 private  String surveyType;

 private  String userDepartment;

 private  Boolean deleted;

 private  Date deletedFrom;

 private  Date deletedTo;

 private  Integer minReported;

 private  Integer minContributions;

 private  Boolean frozen;


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedTo(){
    return generatedTo;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getDeletedFrom(){
    return deletedFrom;
}


public void setEndFrom(Date endFrom){
    this.endFrom = endFrom;
}


public String[] getLanguages(){
    return languages;
}


public void setPublishedFrom(Date publishedFrom){
    this.publishedFrom = publishedFrom;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getStartFrom(){
    return startFrom;
}


public void setMinReported(Integer minReported){
    this.minReported = minReported;
}


public String getSortKey(){
    return sortKey;
}


public String getOwner(){
    return owner;
}


public String getStatus(){
    return status;
}


public void setUid(String uid){
    this.uid = uid;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
}


public void setStartTo(Date startTo){
    this.startTo = startTo;
}


public void setAccess(String access){
    this.access = access;
}


public String getUid(){
    return uid;
}


public String getTitle(){
    return title;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getEndFrom(){
    return endFrom;
}


public void setLanguages(String[] languages){
    this.languages = languages;
}


public void setId(String id){
    this.id = id;
}


public void setEndTo(Date endTo){
    this.endTo = endTo;
}


public void setUser(User user){
    this.user = user;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedFrom(){
    return generatedFrom;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public String getKeywords(){
    return keywords;
}


public void setTitle(String title){
    this.title = title;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getPublishedFrom(){
    return publishedFrom;
}


public void setFirstPublishedFrom(Date firstPublishedFrom){
    this.firstPublishedFrom = firstPublishedFrom;
}


public Integer getMinReported(){
    return minReported;
}


public void setShortname(String name){
    this.shortname = name;
}


public void setDeletedFrom(Date deletedFrom){
    this.deletedFrom = deletedFrom;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getDeletedTo(){
    return deletedTo;
}


public String getSelector(){
    return selector;
}


public String getType(){
    return surveyType;
}


public Boolean getFrozen(){
    return frozen;
}


public void setStartFrom(Date startFrom){
    this.startFrom = startFrom;
}


public void setGeneratedTo(Date generatedTo){
    this.generatedTo = generatedTo;
}


public String getShortname(){
    return shortname;
}


public User getUser(){
    return user;
}


public String getId(){
    return id;
}


public void setGeneratedFrom(Date generatedFrom){
    this.generatedFrom = generatedFrom;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getFirstPublishedFrom(){
    return firstPublishedFrom;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getFirstPublishedTo(){
    return firstPublishedTo;
}


public String getSortOrder(){
    return sortOrder;
}


public Boolean getDeleted(){
    return deleted;
}


public void setSurveys(String surveys){
    this.surveys = surveys;
}


public boolean containsLanguage(String code){
    if (languages != null) {
        for (String c : languages) {
            if (c.equalsIgnoreCase(code))
                return true;
        }
    }
    return false;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getEndTo(){
    return endTo;
}


public String getAccess(){
    return access;
}


public void setFirstPublishedTo(Date firstPublishedTo){
    this.firstPublishedTo = firstPublishedTo;
}


public void setPublishedTo(Date publishedTo){
    this.publishedTo = publishedTo;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getPublishedTo(){
    return publishedTo;
}


public Integer getMinContributions(){
    return minContributions;
}


public void setUserDepartment(String userDepartment){
    this.userDepartment = userDepartment;
}


public void setType(String surveyType){
    this.surveyType = surveyType;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public String getUserDepartment(){
    return userDepartment;
}


public void setDeletedTo(Date deletedTo){
    this.deletedTo = deletedTo;
}


public void setStatus(String status){
    this.status = status;
}


public void setFrozen(Boolean frozen){
    this.frozen = frozen;
}


public void setMinContributions(Integer minContributions){
    this.minContributions = minContributions;
}


public void setSelector(String selector){
    this.selector = selector;
}


public String getSurveys(){
    return surveys;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getStartTo(){
    return startTo;
}


public void setDeleted(Boolean deleted){
    this.deleted = deleted;
}


}