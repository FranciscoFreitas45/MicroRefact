package org.jugbd.mnet.DTO;
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
public class Visit extends PersistentObject{

 private  Long id;

 private  Long version;

 private  Date visitTime;

 private  String comment;

 private  String doctorsName;

 private  Status status;

 private  Register register;

 private  OutdoorRegister outdoorRegister;

 private Long id;

 private Long id;


public Long getVersion(){
    return version;
}


public Long getId(){
    return id;
}


public Status getStatus(){
    return status;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public String getDoctorsName(){
    return doctorsName;
}


public Date getVisitTime(){
    return visitTime;
}


public String getComment(){
    return comment;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


}