package com.yalcin.entity;
 import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "active_sessions", schema = "public")
public class ActiveSessions {

@Id
@Column(name = "refresh_token")
 private  String refreshToken;

@Column(name = "access_token")
 private  String accessToken;

@Column(name = "user_agent")
 private  String userAgent;

@Column(name = "expire_date")
 private  LocalDateTime expireDate;

@Column(name = "issue_date")
 private  LocalDateTime issueDate;

@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
@JsonBackReference
 private  User user;

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


public void setIssueDate(LocalDateTime issueDate){
    this.issueDate = issueDate;
}


public String getUserAgent(){
    return this.userAgent;
}


public void setUserAgent(String userAgent){
    this.userAgent = userAgent;
}


public LocalDateTime getExpireDate(){
    return this.expireDate;
}


public void setUser(User user){
    this.user = user;
}


public void setRefreshToken(String refreshToken){
    this.refreshToken = refreshToken;
}


public void getAccessToken(String accessToken){
    this.accessToken = accessToken;
}


public void setExpirationDate(LocalDateTime expireDate){
    this.expireDate = expireDate;
}


}