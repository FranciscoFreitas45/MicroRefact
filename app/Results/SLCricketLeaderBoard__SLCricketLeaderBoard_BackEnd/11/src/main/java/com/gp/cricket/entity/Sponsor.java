package com.gp.cricket.entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import com.gp.cricket.Request.UserRequest;
import com.gp.cricket.Request.Impl.UserRequestImpl;
import com.gp.cricket.DTO.User;
@Entity
public class Sponsor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "sponsor_id")
 private  Integer sponsorId;

@Column(name = "company_name")
 private  String companyName;

@Column(name = "responsible_person")
 private  String responsiblePerson;

@Transient
 private  User userId;

@Column(name = "userIdv2")
 private Integer userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Sponsor() {
    super();
// TODO Auto-generated constructor stub
}public Sponsor(Integer sponsorId, String companyName, String responsiblePerson, @NotNull User userId) {
    super();
    this.sponsorId = sponsorId;
    this.companyName = companyName;
    this.responsiblePerson = responsiblePerson;
    this.userId = userId;
}
public void setSponsorId(Integer sponsorId){
    this.sponsorId = sponsorId;
}


public String getCompanyName(){
    return companyName;
}


public String getResponsiblePerson(){
    return responsiblePerson;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setResponsiblePerson(String responsiblePerson){
    this.responsiblePerson = responsiblePerson;
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public Integer getSponsorId(){
    return sponsorId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}