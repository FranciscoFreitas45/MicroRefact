package com.gp.cricket.entity;
 import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
@Column(name = "user_id")
 private Integer userId;

@NotBlank(message = "User name is mandatory")
 private String userName;

@NotBlank(message = "Full Name is mandatory")
 private String fullName;

@NotBlank(message = "Name with initial is mandatory")
 private String nameWithInitial;

@NotBlank(message = "NIC is mandatory")
@Size(min = 10, max = 10)
 private String nic;

@NotBlank(message = "Contact number is mandatory")
@Size(min = 10, max = 10, message = "Contact number size should be 10")
 private String contactNumber;

@NotNull
@Min(1)
@Max(5)
 private Byte role;

@NotBlank(message = "Email is mandatory")
@Email(message = "Email should be valid")
 private String email;

@NotBlank(message = "Password is mandatory")
 private String password;

@NotBlank(message = "Address is mandatory")
 private String address;

@NotNull
@DateTimeFormat(pattern = "MM-dd-YYYY")
 private LocalDate regDate;

@NotNull
@Min(0)
@Max(1)
 private Byte status;

@Column(name = "profile_image")
 private String profileImage;

public User() {
    super();
}public User(@NotNull Integer userId, @NotBlank(message = "User name is mandatory") String userName, @NotBlank(message = "Full Name is mandatory") String fullName, @NotBlank(message = "Name with initial is mandatory") String nameWithInitial, @NotBlank(message = "NIC is mandatory") @Size(min = 10, max = 12) String nic, @NotBlank(message = "Contact number is mandatory") @Size(min = 10, max = 10, message = "Contact number size should be 10") String contactNumber, @NotNull @Min(1) @Max(5) Byte type, @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email, @NotBlank(message = "Password is mandatory") String password, @NotBlank(message = "Address is mandatory") String address, @NotNull LocalDate regDate, @NotNull @Min(0) @Max(1) Byte status, @NotNull String profileImage) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.fullName = fullName;
    this.nameWithInitial = nameWithInitial;
    this.nic = nic;
    this.contactNumber = contactNumber;
    this.role = type;
    this.email = email;
    this.password = password;
    this.address = address;
    this.regDate = regDate;
    this.status = status;
    this.profileImage = profileImage;
}
public void setContactNumber(String contactNumber){
    this.contactNumber = contactNumber;
}


public void setPassword(String password){
    this.password = password;
}


public void setProfileImage(String profileImage){
    this.profileImage = profileImage;
}


public void setNic(String nic){
    this.nic = nic;
}


public String getProfileImage(){
    return profileImage;
}


public void setNameWithInitial(String nameWithInitial){
    this.nameWithInitial = nameWithInitial;
}


public void setRegDate(LocalDate regDate){
    this.regDate = regDate;
}


public Byte getStatus(){
    return status;
}


public LocalDate getRegDate(){
    return regDate;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public String getNic(){
    return nic;
}


public String getAddress(){
    return address;
}


public String getNameWithInitial(){
    return nameWithInitial;
}


public String getFullName(){
    return fullName;
}


public String getContactNumber(){
    return contactNumber;
}


public void setAddress(String address){
    this.address = address;
}


public Byte getRole(){
    return role;
}


public void setStatus(Byte status){
    this.status = status;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setRole(Byte type){
    this.role = type;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User [userId=" + userId + ", userName=" + userName + ", fullName=" + fullName + ", nameWithInitial=" + nameWithInitial + ", nic=" + nic + ", contactNumber=" + contactNumber + ", role=" + role + ", email=" + email + ", password=" + password + ", address=" + address + ", regDate=" + regDate + ", status=" + status + "profile image =" + profileImage + "]";
}


public Integer getUserId(){
    return userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}