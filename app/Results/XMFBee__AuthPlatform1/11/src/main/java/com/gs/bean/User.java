package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.Role;
public class User {

 private  String userId;

 private  String userEmail;

 private  String userPhone;

 private  String userPwd;

 private  String userNickname;

 private  String userIdentity;

 private  String userName;

 private  String userGender;

 private  Date userBirthday;

 private  String userAddress;

 private  String qqOpenId;

 private  String weiboOpenId;

 private  String weChatOpenId;

 private  String userIcon;

 private  String userDes;

 private  String companyId;

 private  Double userSalary;

 private  Date userCreatedTime;

 private  Date userLoginedTime;

 private  String userStatus;

 private  String roleId;

 private  int phonecode;

 private  Company company;

 private  Role role;

 private  UserRole userRole;


public void setUserPhone(String userPhone){
    this.userPhone = userPhone;
}


public void setQqOpenId(String qqOpenId){
    this.qqOpenId = qqOpenId;
}


public String getUserIdentity(){
    return userIdentity;
}


public String getUserStatus(){
    return userStatus;
}


public void setWeiboOpenId(String weiboOpenId){
    this.weiboOpenId = weiboOpenId;
}


public void setRoleId(String roleId){
    this.roleId = roleId;
}


public void setUserGender(String userGender){
    this.userGender = userGender;
}


public String getUserDes(){
    return userDes;
}


public String getUserName(){
    return userName;
}


public Date getUserCreatedTime(){
    return userCreatedTime;
}


public String getUserAddress(){
    return userAddress;
}


public Date getUserLoginedTime(){
    return userLoginedTime;
}


public UserRole getUserRole(){
    return userRole;
}


public String getUserIcon(){
    return userIcon;
}


public String getUserNickname(){
    return userNickname;
}


public void setWeChatOpenId(String weChatOpenId){
    this.weChatOpenId = weChatOpenId;
}


public void setUserAddress(String userAddress){
    this.userAddress = userAddress;
}


public Company getCompany(){
    return company;
}


public Date getUserBirthday(){
    return userBirthday;
}


public String getUserGender(){
    return userGender;
}


public String getWeiboOpenId(){
    return weiboOpenId;
}


public void setRole(Role role){
    this.role = role;
}


public int getPhonecode(){
    return phonecode;
}


public String getUserPwd(){
    return userPwd;
}


public String getQqOpenId(){
    return qqOpenId;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setUserEmail(String userEmail){
    this.userEmail = userEmail;
}


public void setUserPwd(String userPwd){
    this.userPwd = userPwd;
}


public String getRoleId(){
    return roleId;
}


public void setUserSalary(Double userSalary){
    this.userSalary = userSalary;
}


public void setUserRole(UserRole userRole){
    this.userRole = userRole;
}


public void setUserIcon(String userIcon){
    this.userIcon = userIcon;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setUserLoginedTime(Date userLoginedTime){
    this.userLoginedTime = userLoginedTime;
}


public void setUserBirthday(Date userBirthday){
    this.userBirthday = userBirthday;
}


public void setUserStatus(String userStatus){
    this.userStatus = userStatus;
}


public void setCompany(Company company){
    this.company = company;
}


public String getUserEmail(){
    return userEmail;
}


public Role getRole(){
    return role;
}


public void setPhonecode(int phonecode){
    this.phonecode = phonecode;
}


public void setUserCreatedTime(Date userCreatedTime){
    this.userCreatedTime = userCreatedTime;
}


public Double getUserSalary(){
    return userSalary;
}


public void setUserIdentity(String userIdentity){
    this.userIdentity = userIdentity;
}


public String getWeChatOpenId(){
    return weChatOpenId;
}


public String getCompanyId(){
    return companyId;
}


public void setUserNickname(String userNickname){
    this.userNickname = userNickname;
}


@Override
public String toString(){
    return "User{" + "userId='" + userId + '\'' + ", userEmail='" + userEmail + '\'' + ", userPhone='" + userPhone + '\'' + ", userPwd='" + userPwd + '\'' + ", userNickname='" + userNickname + '\'' + ", userIdentity='" + userIdentity + '\'' + ", userName='" + userName + '\'' + ", userGender='" + userGender + '\'' + ", userBirthday=" + userBirthday + ", userAddress='" + userAddress + '\'' + ", qqOpenId='" + qqOpenId + '\'' + ", weiboOpenId='" + weiboOpenId + '\'' + ", weChatOpenId='" + weChatOpenId + '\'' + ", userIcon='" + userIcon + '\'' + ", userDes='" + userDes + '\'' + ", companyId='" + companyId + '\'' + ", userSalary=" + userSalary + ", userCreatedTime=" + userCreatedTime + ", userLoginedTime=" + userLoginedTime + ", userStatus='" + userStatus + '\'' + '}';
}


public String getUserPhone(){
    return userPhone;
}


public void setUserDes(String userDes){
    this.userDes = userDes;
}


public String getUserId(){
    return userId;
}


}