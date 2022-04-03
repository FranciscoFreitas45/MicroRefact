package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
public class Examination extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  Boolean anaemia;

 private  Boolean jaundice;

 private  Boolean accessibleLymphNode;

 private  Boolean dehydration;

 private  Boolean oelema;

 private  Boolean neckVein;

 private  String gExaminationComments;

 private  String listeningExamination;

 private  String respiratorySystem;

 private  String gISystem;

 private  String cardiovascularSystem;

 private  String urogenitalSystem;

 private  String nervousSystem;

 private  Register register;

 private  OutdoorRegister outdoorRegister;

 private  String comments;

 private Long id;

 private Long id;


public Long getId(){
    return id;
}


public String getComments(){
    return comments;
}


public Boolean getJaundice(){
    return jaundice;
}


public Boolean getAnaemia(){
    return anaemia;
}


public Boolean getOelema(){
    return oelema;
}


public Boolean getAccessibleLymphNode(){
    return accessibleLymphNode;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


public String getCardiovascularSystem(){
    return cardiovascularSystem;
}


public Long getVersion(){
    return version;
}


public Boolean getNeckVein(){
    return neckVein;
}


public String getgISystem(){
    return gISystem;
}


public String getgExaminationComments(){
    return gExaminationComments;
}


public String getRespiratorySystem(){
    return respiratorySystem;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public String getUrogenitalSystem(){
    return urogenitalSystem;
}


public String getNervousSystem(){
    return nervousSystem;
}


public Boolean getDehydration(){
    return dehydration;
}


public String getListeningExamination(){
    return listeningExamination;
}


}