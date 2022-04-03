package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.ChildBornWith;
import javax.persistence;
import javax.validation.constraints.Size;
public class ChiefComplaint extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Integer hoursOfBurn;

 private  Integer daysOfBurns;

 private  ChildBornWith childBornWith;

 private  String childBornWithOther;

 private  Integer hoursOfTrauma;

 private  Integer daysOfTrauma;

 private  Integer hoursOfInfection;

 private  Integer daysOfInfection;

 private  Integer daysOfUlcerOrSwellingFor;

 private  Integer monthOfUlcerOrSwellingFor;

 private  Integer yearsOfUlcerOrSwellingFor;

 private  String breastRelatedComplaint;

 private  String presentIllness;

 private  Register register;

 private  OutdoorRegister outdoorRegister;

 private  String comments;


public Long getId(){
    return id;
}


public Integer getYearsOfUlcerOrSwellingFor(){
    return yearsOfUlcerOrSwellingFor;
}


public Integer getDaysOfBurns(){
    return daysOfBurns;
}


public String getComments(){
    return comments;
}


public Integer getDaysOfUlcerOrSwellingFor(){
    return daysOfUlcerOrSwellingFor;
}


public Integer getDaysOfInfection(){
    return daysOfInfection;
}


public Integer getDaysOfTrauma(){
    return daysOfTrauma;
}


public OutdoorRegister getOutdoorRegister(){
    return outdoorRegister;
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


public ChildBornWith getChildBornWith(){
    return childBornWith;
}


public Register getRegister(){
    return register;
}


public Integer getMonthOfUlcerOrSwellingFor(){
    return monthOfUlcerOrSwellingFor;
}


public String getPresentIllness(){
    return presentIllness;
}


public Integer getHoursOfInfection(){
    return hoursOfInfection;
}


}