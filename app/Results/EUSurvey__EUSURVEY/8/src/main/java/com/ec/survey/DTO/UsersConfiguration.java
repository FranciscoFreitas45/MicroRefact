package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class UsersConfiguration {

 private  long serialVersionUID;

 private  Integer id;

 private  int userId;

 private  boolean showName;

 private  boolean showEmail;

 private  boolean showOtherEmail;

 private  boolean showLanguage;

 private  boolean showRoles;

 private  boolean showComment;

 private  boolean showBanned;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Column(name = "UC_EMAIL")
public boolean getShowEmail(){
    return showEmail;
}


@Id
@Column(name = "UC_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "UC_ROLES")
public boolean getShowRoles(){
    return showRoles;
}


@Column(name = "UC_BANNED")
public boolean getShowBanned(){
    return showBanned;
}


@Column(name = "UC_COMM")
public boolean getShowComment(){
    return showComment;
}


@Column(name = "UC_LANG")
public boolean getShowLanguage(){
    return showLanguage;
}


@Column(name = "UC_NAME")
public boolean getShowName(){
    return showName;
}


@Column(name = "UC_USER")
public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowName(boolean showName){
    this.showName = showName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowName"))

.queryParam("showName",showName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowEmail(boolean showEmail){
    this.showEmail = showEmail;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowEmail"))

.queryParam("showEmail",showEmail)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowOtherEmail(Boolean showOtherEmail){
    this.showOtherEmail = showOtherEmail != null && showOtherEmail;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowOtherEmail"))

.queryParam("showOtherEmail",showOtherEmail)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowLanguage(boolean showLanguage){
    this.showLanguage = showLanguage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowLanguage"))

.queryParam("showLanguage",showLanguage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowRoles(boolean showRoles){
    this.showRoles = showRoles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowRoles"))

.queryParam("showRoles",showRoles)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowComment(boolean showComment){
    this.showComment = showComment;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowComment"))

.queryParam("showComment",showComment)
;
restTemplate.put(builder.toUriString(),null);
}


}