package com.gp.cricket.entity;
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
@Entity
public class Club {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "club_id")
 private  Integer clubId;

@NotBlank(message = "Club name is mandatory")
 private  String clubName;

@NotBlank(message = "Address is mandatory")
 private  String address;

@NotBlank(message = "Email is mandatory")
@Email(message = "Email should be valid")
 private  String email;

@NotBlank(message = "Contact number is mandatory")
@Size(min = 10, max = 10, message = "Contact number size should be 10")
 private  String contactNumber;

@NotNull
@DateTimeFormat(pattern = "MM-dd-YYYY")
 private  LocalDate regDate;

@NotNull
@Min(0)
@Max(1)
 private  Byte status;

 private  String clubLogo;

@Transient
 private  Manager managerId;

@Column(name = "managerIdv2")
 private Integer managerIdv2;

@Transient
 private ManagerRequest managerrequest = new ManagerRequestImpl();;

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
public void setContactNumber(String contactNumber){
    this.contactNumber = contactNumber;
}


public String getContactNumber(){
    return contactNumber;
}


public Manager getManagerId(){
  this.managerId = managerrequest.getManagerId(this.managerIdv2);
return this.managerId;
}


public void setManagerId(Manager managerId){
 managerrequest.setManagerId(managerId,this.managerIdv2);
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


public void setRegDate(LocalDate regDate){
    this.regDate = regDate;
}


public Byte getStatus(){
    return status;
}


public void setStatus(Byte status){
    this.status = status;
}


public void setEmail(String email){
    this.email = email;
}


public LocalDate getRegDate(){
    return regDate;
}


public void setClubId(Integer clubId){
    this.clubId = clubId;
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


@Override
public String toString(){
    return "Club [clubId=" + clubId + ", clubName=" + clubName + ", address=" + address + ", email=" + email + ", contactNumber=" + contactNumber + ", regDate=" + regDate + ", status=" + status + ", clubLogo=" + clubLogo + ", managerId=" + managerId + "]";
}


public void setClubName(String clubName){
    this.clubName = clubName;
}


public void setClubLogo(String clubLogo){
    this.clubLogo = clubLogo;
}


}