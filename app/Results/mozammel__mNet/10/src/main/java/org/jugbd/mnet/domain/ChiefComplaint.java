package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.ChildBornWith;
import javax.persistence;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
@Entity
public class ChiefComplaint extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  Integer hoursOfBurn;

 private  Integer daysOfBurns;

@Column(length = 20)
@Enumerated(EnumType.STRING)
 private  ChildBornWith childBornWith;

@Size(max = 100)
 private  String childBornWithOther;

 private  Integer hoursOfTrauma;

 private  Integer daysOfTrauma;

 private  Integer hoursOfInfection;

 private  Integer daysOfInfection;

 private  Integer daysOfUlcerOrSwellingFor;

 private  Integer monthOfUlcerOrSwellingFor;

 private  Integer yearsOfUlcerOrSwellingFor;

@Size(max = 1000)
 private  String breastRelatedComplaint;

@Size(max = 1000)
 private  String presentIllness;

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


public void setMonthOfUlcerOrSwellingFor(Integer monthOfUlcerOrSwellingFor){
    this.monthOfUlcerOrSwellingFor = monthOfUlcerOrSwellingFor;
}


public void setPresentIllness(String presentIllness){
    this.presentIllness = presentIllness;
}


public void setDaysOfUlcerOrSwellingFor(Integer daysOfUlcerOrSwellingFor){
    this.daysOfUlcerOrSwellingFor = daysOfUlcerOrSwellingFor;
}


public Long getId(){
    return id;
}


public ChiefComplaint setRegister(Register register){
  this.register = registerrequest.setRegister(register,this.id);
return this.register;
}


public void setDaysOfBurns(Integer daysOfBurns){
    this.daysOfBurns = daysOfBurns;
}


public Integer getYearsOfUlcerOrSwellingFor(){
    return yearsOfUlcerOrSwellingFor;
}


public ChiefComplaint setChildBornWithOther(String childBornWithOther){
    this.childBornWithOther = childBornWithOther;
    return this;
}


public ChiefComplaint setOutdoorRegister(OutdoorRegister outdoorRegister){
  this.outdoorRegister = outdoorregisterrequest.setOutdoorRegister(outdoorRegister,this.id);
return this.outdoorRegister;
}


public Integer getDaysOfBurns(){
    return daysOfBurns;
}


public String getComments(){
    return comments;
}


public void setId(Long id){
    this.id = id;
}


public void setHoursOfTrauma(Integer hoursOfTrauma){
    this.hoursOfTrauma = hoursOfTrauma;
}


public Integer getDaysOfUlcerOrSwellingFor(){
    return daysOfUlcerOrSwellingFor;
}


public Integer getDaysOfInfection(){
    return daysOfInfection;
}


public void setChildBornWith(ChildBornWith childBornWith){
    this.childBornWith = childBornWith;
}


public void setComments(String comments){
    this.comments = comments;
}


public Integer getDaysOfTrauma(){
    return daysOfTrauma;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


public String getBreastRelatedComplaint(){
    return breastRelatedComplaint;
}


public Integer getHoursOfBurn(){
    return hoursOfBurn;
}


public String getChildBornWithOther(){
    return childBornWithOther;
}


public Integer getHoursOfTrauma(){
    return hoursOfTrauma;
}


public void setBreastRelatedComplaint(String breastRelatedComplaint){
    this.breastRelatedComplaint = breastRelatedComplaint;
}


public void setYearsOfUlcerOrSwellingFor(Integer yearsOfUlcerOrSwellingFor){
    this.yearsOfUlcerOrSwellingFor = yearsOfUlcerOrSwellingFor;
}


public ChildBornWith getChildBornWith(){
    return childBornWith;
}


public void setDaysOfInfection(Integer daysOfInfection){
    this.daysOfInfection = daysOfInfection;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public void setHoursOfBurn(Integer hoursOfBurn){
    this.hoursOfBurn = hoursOfBurn;
}


public Integer getMonthOfUlcerOrSwellingFor(){
    return monthOfUlcerOrSwellingFor;
}


public void setDaysOfTrauma(Integer daysOfTrauma){
    this.daysOfTrauma = daysOfTrauma;
}


public String getPresentIllness(){
    return presentIllness;
}


public void setHoursOfInfection(Integer hoursOfInfection){
    this.hoursOfInfection = hoursOfInfection;
}


public Integer getHoursOfInfection(){
    return hoursOfInfection;
}


}