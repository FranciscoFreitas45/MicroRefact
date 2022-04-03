package com.evolvingreality.onleave.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.evolvingreality.onleave.Request.UserRequest;
import com.evolvingreality.onleave.Request.Impl.UserRequestImpl;
import com.evolvingreality.onleave.DTO.User;
@Entity
@Table(name = "ENTITLEMENT")
public class Entitlement extends AbstractAuditingEntity{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Column(name = "year")
 private  Integer year;

@Column(name = "annual_leave")
 private  Double annualLeave;

@Column(name = "additional_annual_leave")
 private  Double additionalAnnualLeave;

@Transient
 private  User user;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public Integer getYear(){
    return year;
}


public void setAdditionalAnnualLeave(Double additionalAnnualLeave){
    this.additionalAnnualLeave = additionalAnnualLeave;
}


public Double getAnnualLeave(){
    return annualLeave;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public Double getAdditionalAnnualLeave(){
    return additionalAnnualLeave;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public void setYear(Integer year){
    this.year = year;
}


public void setAnnualLeave(Double annualLeave){
    this.annualLeave = annualLeave;
}


}