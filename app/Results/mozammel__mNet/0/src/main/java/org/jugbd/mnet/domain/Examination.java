package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
@Entity
public class Examination extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

 private  Boolean anaemia;

 private  Boolean jaundice;

 private  Boolean accessibleLymphNode;

 private  Boolean dehydration;

 private  Boolean oelema;

 private  Boolean neckVein;

@Size(max = 1000)
 private  String gExaminationComments;

@Column(length = 1000)
@Size(max = 1000)
 private  String listeningExamination;

@Size(max = 1000)
 private  String respiratorySystem;

@Size(max = 1000)
 private  String gISystem;

@Size(max = 1000)
 private  String cardiovascularSystem;

@Size(max = 1000)
 private  String urogenitalSystem;

@Size(max = 1000)
 private  String nervousSystem;

@Transient
 private  Register register;

@Transient
 private  OutdoorRegister outdoorRegister;

@Size(max = 1000)
 private  String comments;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private OutdoorRegisterRequest outdoorregisterrequest = new OutdoorRegisterRequestImpl();;


public void setUrogenitalSystem(String urogenitalSystem){
    this.urogenitalSystem = urogenitalSystem;
}


public Long getId(){
    return id;
}


public void setgISystem(String gISystem){
    this.gISystem = gISystem;
}


public Examination setRegister(Register register){
  this.register = registerrequest.setRegister(register,this.id);
return this.register;
}


public Examination setOutdoorRegister(OutdoorRegister outdoorRegister){
  this.outdoorRegister = outdoorregisterrequest.setOutdoorRegister(outdoorRegister,this.id);
return this.outdoorRegister;
}


public void setListeningExamination(String listeningExamination){
    this.listeningExamination = listeningExamination;
}


public void setJaundice(Boolean jaundice){
    this.jaundice = jaundice;
}


public void setAccessibleLymphNode(Boolean accessibleLymphNode){
    this.accessibleLymphNode = accessibleLymphNode;
}


public void setDehydration(Boolean dehydration){
    this.dehydration = dehydration;
}


public String getComments(){
    return comments;
}


public void setId(Long id){
    this.id = id;
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


public void setComments(String comments){
    this.comments = comments;
}


public void setOelema(Boolean oelema){
    this.oelema = oelema;
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


public void setAnaemia(Boolean anaemia){
    this.anaemia = anaemia;
}


public Boolean getNeckVein(){
    return neckVein;
}


public void setVersion(Long version){
    this.version = version;
}


public String getgISystem(){
    return gISystem;
}


public void setgExaminationComments(String gExaminationComments){
    this.gExaminationComments = gExaminationComments;
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


public void setNeckVein(Boolean neckVein){
    this.neckVein = neckVein;
}


public void setNervousSystem(String nervousSystem){
    this.nervousSystem = nervousSystem;
}


public String getUrogenitalSystem(){
    return urogenitalSystem;
}


public String getNervousSystem(){
    return nervousSystem;
}


public void setRespiratorySystem(String respiratorySystem){
    this.respiratorySystem = respiratorySystem;
}


public Boolean getDehydration(){
    return dehydration;
}


public String getListeningExamination(){
    return listeningExamination;
}


public void setCardiovascularSystem(String cardiovascularSystem){
    this.cardiovascularSystem = cardiovascularSystem;
}


}