package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
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


public String getComplaintReplyUser(){
    return complaintReplyUser;
}


public void setComplaintReplyUser(String complaintReplyUser){
    this.complaintReplyUser = complaintReplyUser;
}


public String getComplaintReply(){
    return complaintReply;
}


public void setComplaintReplyTime(Date complaintReplyTime){
    this.complaintReplyTime = complaintReplyTime;
}


public void setComplaintCreatedTime(Date complaintCreatedTime){
    this.complaintCreatedTime = complaintCreatedTime;
}


public User getUser(){
    return user;
}


public void setComplaintId(String complaintId){
    this.complaintId = complaintId;
}


public void setComplaintContent(String complaintContent){
    this.complaintContent = complaintContent;
}


public void setComplaintReply(String complaintReply){
    this.complaintReply = complaintReply;
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


public void setUser(User user){
    this.user = user;
}


public void setAdmin(User admin){
    this.admin = admin;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public Date getComplaintReplyTime(){
    return complaintReplyTime;
}


public void setCompany(Company company){
    this.company = company;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}