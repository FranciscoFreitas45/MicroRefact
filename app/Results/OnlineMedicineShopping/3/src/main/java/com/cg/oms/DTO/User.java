package com.cg.oms.DTO;
 import java.time.LocalDateTime;
import javax.persistence.CascadeType;
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
public class User {

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

 private  Role role;

 private  Address userAddress;

 private Long roleId;

 private Integer addressId;

/**
 * creating default constructors
 */
public User() {
    super();
}
public String getEmailId(){
    return emailId;
}


public String getUserPassword(){
    return userPassword;
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


public String getUserGender(){
    return userGender;
}


public Integer getUserAge(){
    return userAge;
}


public String getFirstName(){
    return firstName;
}


public Long getUserId(){
    return userId;
}


}