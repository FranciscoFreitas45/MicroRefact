package com.gs.DTO;
 import java.util.Date;
public class Complaint {

 private  String complaintId;

 private  String userId;

 private  String complaintContent;

 private  Date complaintCreatedTime;

 private  String complaintReply;

 private  Date complaintReplyTime;

 private  String complaintReplyUser;

 private  String companyId;

 private  User user;

 private  User admin;

 private  Company company;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public String getComplaintReplyUser(){
    return complaintReplyUser;
}


public String getComplaintReply(){
    return complaintReply;
}


public void setComplaintCreatedTime(Date complaintCreatedTime){
    this.complaintCreatedTime = complaintCreatedTime;
}


public User getUser(){
    return user;
}


public void setComplaintContent(String complaintContent){
    this.complaintContent = complaintContent;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public Date getComplaintCreatedTime(){
    return complaintCreatedTime;
}


public String getComplaintId(){
    return complaintId;
}


public User getAdmin(){
    return admin;
}


public String getComplaintContent(){
    return complaintContent;
}


public void setAdmin(User admin){
    this.admin = admin;
}


public Date getComplaintReplyTime(){
    return complaintReplyTime;
}


public String getUserId(){
    return userId;
}


public void setComplaintReplyUser(String complaintReplyUser){
    this.complaintReplyUser = complaintReplyUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setComplaintReplyUser"))

.queryParam("complaintReplyUser",complaintReplyUser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setComplaintReply(String complaintReply){
    this.complaintReply = complaintReply;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setComplaintReply"))

.queryParam("complaintReply",complaintReply)
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