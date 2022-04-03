package com.gp.cricket.DTO;
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
public class User {

 private Integer userId;

 private String userName;

 private String fullName;

 private String nameWithInitial;

 private String nic;

 private String contactNumber;

 private Byte role;

 private String email;

 private String password;

 private String address;

 private LocalDate regDate;

 private Byte status;

 private String profileImage;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

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
public String getProfileImage(){
    return profileImage;
}


public Byte getStatus(){
    return status;
}


public LocalDate getRegDate(){
    return regDate;
}


public String getUserName(){
    return userName;
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


public Byte getRole(){
    return role;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public Integer getUserId(){
    return userId;
}


public void setStatus(Byte status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ userId).concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


}