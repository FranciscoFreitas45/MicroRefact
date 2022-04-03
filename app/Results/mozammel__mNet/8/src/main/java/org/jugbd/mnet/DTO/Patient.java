package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public class Patient extends PersistentObjectimplements Auditable{

 private  Logger log;

 private  long serialVersionUID;

 private  Long id;

 private  Long version;

 private  String healthId;

 private  String name;

 private  Date dateOfBirth;

 private  Integer age;

 private  Integer day;

 private  Integer month;

 private  Integer year;

 private  Gender gender;

 private  BloodType bloodType;

 private  String contactNumber;

 private  String nid;

 private  Address address;

 private  Boolean dead;

 private  Date deathDate;

 private  MaritalStatus maritalStatus;

 private  Occupation occupation;

 private  EducationLevel educationLevel;

 private  Integer ageEstimated;

 private  boolean birthdateEstimated;

 private  Set<Register> registers;

 private  Set<OutdoorRegister> outdoorRegisters;

public Patient() {
}
public Integer getAge(Date onDate){
    if (dateOfBirth == null) {
        if (ageEstimated != null) {
            return ageEstimated;
        }
        return null;
    }
    log.debug("dateOfBirth is not null");
    // Use default end date as today.
    Calendar today = Calendar.getInstance();
    // But if given, use the given date.
    if (onDate != null) {
        today.setTime(onDate);
    }
    // If date given is after date of death then use date of death as end date
    if (getDeathDate() != null && today.getTime().after(getDeathDate())) {
        today.setTime(getDeathDate());
    }
    Calendar bday = Calendar.getInstance();
    bday.setTime(dateOfBirth);
    int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
    // Adjust age when today's date is before the person's birthday
    int todaysMonth = today.get(Calendar.MONTH);
    int bdayMonth = bday.get(Calendar.MONTH);
    int todaysDay = today.get(Calendar.DAY_OF_MONTH);
    int bdayDay = bday.get(Calendar.DAY_OF_MONTH);
    if (todaysMonth < bdayMonth) {
        age--;
    } else if (todaysMonth == bdayMonth && todaysDay < bdayDay) {
        // we're only comparing on month and day, not minutes, etc
        age--;
    }
    return age;
}


public String getName(){
    return name;
}


public Date getDeathDate(){
    return deathDate;
}


public Set<OutdoorRegister> getOutdoorRegisters(){
    return outdoorRegisters;
}


public MaritalStatus getMaritalStatus(){
    return maritalStatus;
}


public Occupation getOccupation(){
    return occupation;
}


public Long getId(){
    return id;
}


public String getHealthId(){
    return healthId;
}


@Valid
public Address getAddress(){
    return address;
}


public BloodType getBloodType(){
    return bloodType;
}


public String getNid(){
    return nid;
}


public Integer getMonth(){
    return month;
}


public Long getVersion(){
    return version;
}


public String getContactNumber(){
    return contactNumber;
}


public Date getDateOfBirth(){
    return dateOfBirth;
}


public Boolean getDead(){
    return dead;
}


public Gender getGender(){
    return gender;
}


public Set<Register> getRegisters(){
    return registers;
}


public Integer getDay(){
    return day;
}


public EducationLevel getEducationLevel(){
    return educationLevel;
}


public Integer getYear(){
    return year;
}


public Integer getAgeEstimated(){
    return ageEstimated;
}


public boolean getBirthdateEstimated(){
    return birthdateEstimated;
}


}