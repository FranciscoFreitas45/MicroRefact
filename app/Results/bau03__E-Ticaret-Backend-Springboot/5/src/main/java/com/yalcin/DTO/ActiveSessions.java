package com.yalcin.DTO;
 import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
public class ActiveSessions {

 private  String refreshToken;

 private  String accessToken;

 private  String userAgent;

 private  LocalDateTime expireDate;

 private  LocalDateTime issueDate;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public ActiveSessions() {
}public ActiveSessions(String refreshToken, String accessToken, String userAgent, LocalDateTime expireDate, LocalDateTime issueDate) {
    this.refreshToken = refreshToken;
    this.accessToken = accessToken;
    this.userAgent = userAgent;
    this.expireDate = expireDate;
    this.issueDate = issueDate;
}
public String getRefreshToken(){
    return this.refreshToken;
}


public User getUser(){
    return this.user;
}


public LocalDateTime getIssueDate(){
    return this.issueDate;
}


public String getUserAgent(){
    return this.userAgent;
}


public LocalDateTime getExpireDate(){
    return this.expireDate;
}


public void setUser(User user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ refreshToken).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


}