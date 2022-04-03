package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Outcome;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
@Entity
public class ComplicationManagement extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@NotEmpty
@Size(max = 2000)
 private  String postOperativeComplication;

@Size(max = 2000)
 private  String managementOfComplication;

@NotNull
@Column(length = 12)
@Enumerated(EnumType.STRING)
 private  Outcome outcome;

@Size(max = 100)
 private  String comment;

@NotNull
 private  Integer hospitalStays;

@Size(max = 2000)
 private  String caseSummery;

@Transient
 private  Register register;

@Column(name = "register_id_bklink")
 private  Long registerId;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public Long getVersion(){
    return version;
}


public void setHospitalStays(Integer hospitalStays){
    this.hospitalStays = hospitalStays;
}


public void setManagementOfComplication(String managementOfComplication){
    this.managementOfComplication = managementOfComplication;
}


public Integer getHospitalStays(){
    return hospitalStays;
}


public void setVersion(Long version){
    this.version = version;
}


public String getManagementOfComplication(){
    return managementOfComplication;
}


public Outcome getOutcome(){
    return outcome;
}


public void setPostOperativeComplication(String postOperativeComplication){
    this.postOperativeComplication = postOperativeComplication;
}


@Override
public Long getId(){
    return id;
}


public String getCaseSummery(){
    return caseSummery;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public void setCaseSummery(String caseSummery){
    this.caseSummery = caseSummery;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public ComplicationManagement setRegisterId(Long registerId){
    this.registerId = registerId;
    return this;
}


public String getPostOperativeComplication(){
    return postOperativeComplication;
}


public void setId(Long id){
    this.id = id;
}


public void setComment(String comment){
    this.comment = comment;
}


public void setOutcome(Outcome outcome){
    this.outcome = outcome;
}


public String getComment(){
    return comment;
}


public Long getRegisterId(){
    return registerId;
}


}