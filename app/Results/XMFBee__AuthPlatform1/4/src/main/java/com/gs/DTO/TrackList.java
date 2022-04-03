package com.gs.DTO;
 import java.util.Date;
public class TrackList {

 private  String trackId;

 private  String userId;

 private  String trackContent;

 private  Integer serviceEvaluate;

 private  String trackUser;

 private  Date trackCreatedTime;

 private  String companyId;

 private  Company company;

 private  User user;

 private  Checkin checkin;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://13";


public String getTrackContent(){
    return trackContent;
}


public User getUser(){
    return user;
}


public String getTrackUser(){
    return trackUser;
}


public Date getTrackCreatedTime(){
    return trackCreatedTime;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public void setCheckin(Checkin checkin){
    this.checkin = checkin;
}


public Integer getServiceEvaluate(){
    return serviceEvaluate;
}


public Checkin getCheckin(){
    return checkin;
}


public void setServiceEvaluate(Integer serviceEvaluate){
    this.serviceEvaluate = serviceEvaluate;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public String getTrackId(){
    return trackId;
}


public String getUserId(){
    return userId;
}


public void setTrackUser(String trackUser){
    this.trackUser = trackUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTrackUser"))

.queryParam("trackUser",trackUser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTrackContent(String trackContent){
    this.trackContent = trackContent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTrackContent"))

.queryParam("trackContent",trackContent)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(String userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


}