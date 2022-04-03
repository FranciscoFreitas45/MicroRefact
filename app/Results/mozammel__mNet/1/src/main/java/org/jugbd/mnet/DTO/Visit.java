package org.jugbd.mnet.DTO;
 import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Status;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.Date;
public class Visit extends PersistentObject{

 private  Long id;

 private  Long version;

 private  Date visitTime;

 private  String comment;

 private  String doctorsName;

 private  Status status;

 private  Register register;

 private  OutdoorRegister outdoorRegister;


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
    this.register = register;
    return this;
}


public Status getStatus(){
    return status;
}


public Visit setStatus(Status status){
    this.status = status;
    return this;
}


public Register getRegister(){
    return register;
}


public String getDoctorsName(){
    return doctorsName;
}


public Visit setOutdoorRegister(OutdoorRegister outdoorRegister){
    this.outdoorRegister = outdoorRegister;
    return this;
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
    return outdoorRegister;
}


}