package org.jugbd.mnet.domain;
 import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Status;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.Date;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
@Entity
public class Visit extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@Temporal(TemporalType.TIMESTAMP)
 private  Date visitTime;

@NotEmpty(message = "Visit note can not be empty")
@Size(max = 3000)
@Column(length = 3000)
 private  String comment;

@NotEmpty(message = "Please put the name doctor's name")
@Size(max = 120)
 private  String doctorsName;

@Enumerated(value = EnumType.STRING)
@Column(length = 10)
 private  Status status;

@Transient
 private  Register register;

@Transient
 private  OutdoorRegister outdoorRegister;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private OutdoorRegisterRequest outdoorregisterrequest = new OutdoorRegisterRequestImpl();;


public Long getVersion(){
    return version;
}


public Visit setDoctorsName(String doctorsName){
    this.doctorsName = doctorsName;
    return this;
}


public void setVersion(Long version){
    this.version = version;
}


public Long getId(){
    return id;
}


public Visit setRegister(Register register){
  this.register = registerrequest.setRegister(register,this.id);
return this.register;
}


public Status getStatus(){
    return status;
}


public Visit setStatus(Status status){
    this.status = status;
    return this;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public String getDoctorsName(){
    return doctorsName;
}


public Visit setOutdoorRegister(OutdoorRegister outdoorRegister){
  this.outdoorRegister = outdoorregisterrequest.setOutdoorRegister(outdoorRegister,this.id);
return this.outdoorRegister;
}


public Date getVisitTime(){
    return visitTime;
}


public void setId(Long id){
    this.id = id;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getComment(){
    return comment;
}


public void setVisitTime(Date visitTime){
    this.visitTime = visitTime;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


}