package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.User;
import com.gs.Interface.Checkin;
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


public void setTrackCreatedTime(Date trackCreatedTime){
    this.trackCreatedTime = trackCreatedTime;
}


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


public void setTrackUser(String trackUser){
    this.trackUser = trackUser;
}


public void setCheckin(Checkin checkin){
    this.checkin = checkin;
}


public Integer getServiceEvaluate(){
    return serviceEvaluate;
}


public void setTrackContent(String trackContent){
    this.trackContent = trackContent;
}


public Checkin getCheckin(){
    return checkin;
}


public void setTrackId(String trackId){
    this.trackId = trackId;
}


public void setServiceEvaluate(Integer serviceEvaluate){
    this.serviceEvaluate = serviceEvaluate;
}


public void setUser(User user){
    this.user = user;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


public String getTrackId(){
    return trackId;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}