package com.gp.cricket.DTO;
 import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import com.gp.cricket.Request.ManagerRequest;
import com.gp.cricket.Request.Impl.ManagerRequestImpl;
import com.gp.cricket.DTO.Manager;
public class Club {

 private  Integer clubId;

 private  String clubName;

 private  String address;

 private  String email;

 private  String contactNumber;

 private  LocalDate regDate;

 private  Byte status;

 private  String clubLogo;

 private  Manager managerId;

public Club() {
}public Club(Integer clubId, @NotBlank(message = "Club name is mandatory") String clubName, @NotBlank(message = "Address is mandatory") String address, @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email, @NotBlank(message = "Contact number is mandatory") @Size(min = 10, max = 10, message = "Contact number size should be 10") String contactNumber, @NotNull LocalDate regDate, @NotNull @Min(0) @Max(1) Byte status, String clubLogo, @NotNull Manager managerId) {
    super();
    this.clubId = clubId;
    this.clubName = clubName;
    this.address = address;
    this.email = email;
    this.contactNumber = contactNumber;
    this.regDate = regDate;
    this.status = status;
    this.clubLogo = clubLogo;
    this.managerId = managerId;
}
public String getContactNumber(){
    return contactNumber;
}


public Manager getManagerId(){
    return managerId;
}


public void setAddress(String address){
    this.address = address;
}


public Integer getClubId(){
    return clubId;
}


public String getClubLogo(){
    return clubLogo;
}


public Byte getStatus(){
    return status;
}


public void setEmail(String email){
    this.email = email;
}


public LocalDate getRegDate(){
    return regDate;
}


public String getClubName(){
    return clubName;
}


public String getEmail(){
    return email;
}


public String getAddress(){
    return address;
}


public void setClubName(String clubName){
    this.clubName = clubName;
}


}