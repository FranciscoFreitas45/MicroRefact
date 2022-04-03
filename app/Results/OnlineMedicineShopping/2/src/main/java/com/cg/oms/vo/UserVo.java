package com.cg.oms.vo;
 import java.io.Serializable;
import java.time.LocalDateTime;
import com.cg.oms.DTO.Address;
import com.cg.oms.DTO.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.cg.oms.Interface.Role;
//import com.cg.oms.Interface.Address;
public class UserVo implements Serializable{

 private  long serialVersionUID;

@JsonProperty(value = "userId")
 private  Long userId;

@JsonProperty(value = "emailId")
 private  String emailId;

@JsonProperty(value = "firstName")
 private  String firstName;

@JsonProperty(value = "lastName")
 private  String lastName;

@JsonProperty(value = "userAge")
 private  Integer userAge;

@JsonProperty(value = "userGender")
 private  String userGender;

@JsonProperty(value = "userPhoneNumber")
 private  String userPhoneNumber;

 private  String userPassword;

 private  String previousPassword1;

 private  String previousPassword2;

@JsonProperty(value = "createdDate")
 private  LocalDateTime createdDate;

 private  Role role;

 private  Address userAddress;


public void setEmailId(String emailId){
    this.emailId = emailId;
}


public String getEmailId(){
    return emailId;
}


public String getUserPassword(){
    return userPassword;
}


public void setUserAge(Integer userAge){
    this.userAge = userAge;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setUserGender(String userGender){
    this.userGender = userGender;
}


public LocalDateTime getCreatedDate(){
    return createdDate;
}


public Address getUserAddress(){
    return userAddress;
}


public String getPreviousPassword1(){
    return previousPassword1;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public String getPreviousPassword2(){
    return previousPassword2;
}


public Role getRole(){
    return role;
}


public String getLastName(){
    return lastName;
}


public String getUserPhoneNumber(){
    return userPhoneNumber;
}


public void setUserAddress(Address userAddress){
    this.userAddress = userAddress;
}


public void setCreatedDate(LocalDateTime createdDate){
    this.createdDate = createdDate;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getUserGender(){
    return userGender;
}


public void setUserPhoneNumber(String userPhone){
    this.userPhoneNumber = userPhone;
}


public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}


public void setRole(Role role){
    this.role = role;
}


public Integer getUserAge(){
    return userAge;
}


public String getFirstName(){
    return firstName;
}


public void setPreviousPassword2(String previousPassword2){
    this.previousPassword2 = previousPassword2;
}


public Long getUserId(){
    return userId;
}


public void setPreviousPassword1(String previousPassword1){
    this.previousPassword1 = previousPassword1;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}