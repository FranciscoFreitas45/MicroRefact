package com.cg.oms.model;
 import java.time.LocalDateTime;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.cg.oms.Request.RoleRequest;
import com.cg.oms.Request.Impl.RoleRequestImpl;
import com.cg.oms.DTO.Role;
import com.cg.oms.Request.AddressRequest;
import com.cg.oms.Request.Impl.AddressRequestImpl;
import com.cg.oms.DTO.Address;
@Entity
@Table(name = "user_table")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long userId;

 private  String emailId;

 private  String firstName;

 private  String lastName;

 private  Integer userAge;

 private  String userGender;

 private  String userPhoneNumber;

 private  String userPassword;

 private  String previousPassword1;

 private  String previousPassword2;

 private  LocalDateTime createdDate;

@Transient
 private  Role role;

@Transient
 private  Address userAddress;

@Column(name = "roleId")
 private Long roleId;

@Transient
 private RoleRequest rolerequest = new RoleRequestImpl();;

@Column(name = "addressId")
 private Integer addressId;

@Transient
 private AddressRequest addressrequest = new AddressRequestImpl();;

/**
 * creating default constructors
 */
public User() {
    super();
}
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
  this.userAddress = addressrequest.getUserAddress(this.addressId);
return this.userAddress;
}


public String getPreviousPassword1(){
    return previousPassword1;
}


public String getPreviousPassword2(){
    return previousPassword2;
}


public Role getRole(){
  this.role = rolerequest.getRole(this.roleId);
return this.role;
}


public String getLastName(){
    return lastName;
}


public String getUserPhoneNumber(){
    return userPhoneNumber;
}


public void setUserAddress(Address userAddress){
    this.userAddress = userAddress;
    this.addressId = userAddress.getAddressId();
 addressrequest.setUserAddress(userAddress,this.addressId);
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
    this.roleId = role.getRoleId();
 rolerequest.setRole(role,this.roleId);
}



public Integer getUserAge(){
    return userAge;
}


@Override
public String toString(){
    return "User [emailId=" + emailId + ", userAge=" + userAge + ", userGender=" + userGender + ", userPhone=" + userPhoneNumber + ", password=" + userPassword + ", previousPassword1=" + previousPassword1 + ", previousPassword2=" + previousPassword2 + ", createdDate=" + createdDate + ", role=" + role + ", address=" + userAddress + "]";
}


public void setPreviousPassword2(String previousPassword2){
    this.previousPassword2 = previousPassword2;
}


public String getFirstName(){
    return firstName;
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