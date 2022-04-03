package com.ec.survey.DTO;
 public class UserFilter {

 private  String login;

 private  String email;

 private  String comment;

 private  Boolean banned;

 private  Boolean unbanned;

 private  Boolean ECAS;

 private  Boolean system;

 private  Boolean ECASaccess;

 private  Boolean NoECASaccess;

 private  Boolean ECaccess;

 private  Boolean NoECaccess;

 private  String[] roles;

 private  String[] languages;

 private  String sortKey;

 private  String sortOrder;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public Boolean getECaccess(){
    return ECaccess;
}


public String[] getLanguages(){
    return languages;
}


public Boolean getBanned(){
    return banned;
}


public String getSortKey(){
    return sortKey;
}


public String getSortOrder(){
    return sortOrder;
}


public Boolean getSystem(){
    return system;
}


public Boolean getECASaccess(){
    return ECASaccess;
}


public Boolean getECAS(){
    return ECAS;
}


public String getComment(){
    return comment;
}


public Boolean getUnbanned(){
    return this.unbanned;
}


public String[] getRoles(){
    return roles;
}


public Boolean getNoECaccess(){
    return NoECaccess;
}


public String getLogin(){
    return login;
}


public Boolean getNoECASaccess(){
    return NoECASaccess;
}


public String getEmail(){
    return email;
}


public void setLogin(String login){
    this.login = login;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogin"))

.queryParam("login",login)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public void setComment(String comment){
    this.comment = comment;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setComment"))

.queryParam("comment",comment)
;
restTemplate.put(builder.toUriString(),null);
}


public void setECaccess(Boolean eCaccess){
    ECaccess = eCaccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setECaccess"))

.queryParam("eCaccess",eCaccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoECaccess(Boolean noECaccess){
    NoECaccess = noECaccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNoECaccess"))

.queryParam("noECaccess",noECaccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setECASaccess(Boolean eCASaccess){
    ECASaccess = eCASaccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setECASaccess"))

.queryParam("eCASaccess",eCASaccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoECASaccess(Boolean noECASaccess){
    NoECASaccess = noECASaccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNoECASaccess"))

.queryParam("noECASaccess",noECASaccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setECAS(Boolean eCAS){
    ECAS = eCAS;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setECAS"))

.queryParam("eCAS",eCAS)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSystem(Boolean system){
    this.system = system;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSystem"))

.queryParam("system",system)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguages(String[] languages){
    this.languages = languages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguages"))

.queryParam("languages",languages)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBanned(Boolean banned){
    this.banned = banned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBanned"))

.queryParam("banned",banned)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUnbanned(Boolean unbanned){
    this.unbanned = unbanned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUnbanned"))

.queryParam("unbanned",unbanned)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoles(String[] roles){
    this.roles = roles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoles"))

.queryParam("roles",roles)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortKey"))

.queryParam("sortKey",sortKey)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortOrder"))

.queryParam("sortOrder",sortOrder)
;
restTemplate.put(builder.toUriString(),null);
}


}