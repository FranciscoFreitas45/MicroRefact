package com.ec.survey.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedTo(){
    return generatedTo;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getDeletedFrom(){
    return deletedFrom;
}


public String[] getLanguages(){
    return languages;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getStartFrom(){
    return startFrom;
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


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedFrom(){
    return generatedFrom;
}


public String getKeywords(){
    return keywords;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getPublishedFrom(){
    return publishedFrom;
}


public Integer getMinReported(){
    return minReported;
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


public String getShortname(){
    return shortname;
}


public User getUser(){
    return user;
}


public String getId(){
    return id;
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


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getEndTo(){
    return endTo;
}


public String getAccess(){
    return access;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getPublishedTo(){
    return publishedTo;
}


public Integer getMinContributions(){
    return minContributions;
}


public String getUserDepartment(){
    return userDepartment;
}


public String getSurveys(){
    return surveys;
}


@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getStartTo(){
    return startTo;
}


public void setUser(User user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


}