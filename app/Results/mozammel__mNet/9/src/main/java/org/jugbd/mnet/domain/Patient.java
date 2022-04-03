package org.jugbd.mnet.domain;
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
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
@Entity
@Table(name = "patient")
public class Patient extends PersistentObjectimplements Auditable{

 private  Logger log;

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@Size(max = 32)
@Column(length = 32)
 private  String healthId;

@NotEmpty
@Size(max = 100)
@Column(length = 100)
 private  String name;

@Past
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfBirth;

@Max(150)
@Min(0)
@Transient
@Digits(integer = 3, fraction = 0)
 private  Integer age;

@Max(31)
@Min(0)
@Transient
@Digits(integer = 2, fraction = 0)
 private  Integer day;

@Max(11)
@Min(0)
@Transient
@Digits(integer = 2, fraction = 0)
 private  Integer month;

@Max(150)
@Min(0)
@Transient
@Digits(integer = 3, fraction = 0)
 private  Integer year;

@NotNull
@Column(length = 6)
@Enumerated(EnumType.STRING)
 private  Gender gender;

@Column(length = 16)
@Enumerated(EnumType.STRING)
 private  BloodType bloodType;

@NotEmpty
@Size(max = 32)
@Column(length = 32)
@Pattern(regexp = "^01(1|5|6|7|8|9)\\d{8}$", message = "Phone number must be valid ba")
 private  String contactNumber;

@Size(max = 20)
 private  String nid;

@Valid
@Embedded
 private  Address address;

 private  Boolean dead;

@Temporal(TemporalType.DATE)
 private  Date deathDate;

@Column(length = 12)
@Enumerated(EnumType.STRING)
 private  MaritalStatus maritalStatus;

@Column(length = 20)
@Enumerated(EnumType.STRING)
 private  Occupation occupation;

@Column(length = 20)
@Enumerated(EnumType.STRING)
 private  EducationLevel educationLevel;

@Transient
 private  Integer ageEstimated;

 private  boolean birthdateEstimated;

@Transient
 private  Set<Register> registers;

@Transient
 private  Set<OutdoorRegister> outdoorRegisters;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;

@Transient
 private OutdoorRegisterRequest outdoorregisterrequest = new OutdoorRegisterRequestImpl();;

public Patient() {
}
public void setName(String name){
    this.name = name;
}


public void setContactNumber(String contactNumber){
    this.contactNumber = contactNumber;
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


public void setRegisters(Set<Register> registers){
 registerrequest.setRegisters(registers,this.id);
}



public void setNid(String nid){
    this.nid = nid;
}


public void setEducationLevel(EducationLevel educationLevel){
    this.educationLevel = educationLevel;
}


public Set<OutdoorRegister> getOutdoorRegisters(){
  this.outdoorRegisters = outdoorregisterrequest.getOutdoorRegisters(this.id);
return this.outdoorRegisters;
}


public MaritalStatus getMaritalStatus(){
    return maritalStatus;
}


public Occupation getOccupation(){
    return occupation;
}


public void setHealthId(String healthId){
    this.healthId = healthId;
}


public Long getId(){
    return id;
}


public void setBloodType(BloodType bloodType){
    this.bloodType = bloodType;
}


public void setBirthdateFromAge(int age,Date ageOnDate){
    Calendar c = Calendar.getInstance();
    c.setTime(ageOnDate == null ? new Date() : ageOnDate);
    c.set(Calendar.DATE, 1);
    c.set(Calendar.MONTH, Calendar.JANUARY);
    c.add(Calendar.YEAR, -1 * age);
    setDateOfBirth(c.getTime());
    setBirthdateEstimated(true);
}


public void setGender(Gender gender){
    this.gender = gender;
}


public String getHealthId(){
    return healthId;
}


public Patient setDay(Integer day){
    this.day = day;
    return this;
}


public void setId(Long id){
    this.id = id;
}


@Valid
public Address getAddress(){
    return address;
}


public BloodType getBloodType(){
    return bloodType;
}


public void setDeathDate(Date deathDate){
    this.deathDate = deathDate;
}


public String getNid(){
    return nid;
}


public Integer getMonth(){
    return month;
}


public Patient setOutdoorRegisters(Set<OutdoorRegister> outdoorRegisters){
  this.outdoorRegisters = outdoorregisterrequest.setOutdoorRegisters(outdoorRegisters,this.id);
return this.outdoorRegisters;
}


public Long getVersion(){
    return version;
}


public void setOccupation(Occupation occupation){
    this.occupation = occupation;
}


public Patient setMonth(Integer month){
    this.month = month;
    return this;
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


public void setAddress(Address address){
    this.address = address;
}


public void setVersion(Long version){
    this.version = version;
}


public Gender getGender(){
    return gender;
}


public void setMaritalStatus(MaritalStatus maritalStatus){
    this.maritalStatus = maritalStatus;
}


public Set<Register> getRegisters(){
  this.registers = registerrequest.getRegisters(this.id);
return this.registers;
}


public Patient setYear(Integer year){
    this.year = year;
    return this;
}


public Integer getDay(){
    return day;
}


public void setDead(Boolean dead){
    this.dead = dead;
}


public void setAgeEstimated(Integer ageEstimated){
    this.ageEstimated = ageEstimated;
}


public EducationLevel getEducationLevel(){
    return educationLevel;
}


public void setDateOfBirth(Date dateOfBirth){
    this.dateOfBirth = dateOfBirth;
}


public Integer getYear(){
    return year;
}


public Integer getAgeEstimated(){
    return ageEstimated;
}


public void setBirthdateEstimated(boolean birthdateEstimated){
    this.birthdateEstimated = birthdateEstimated;
}


@Override
public String toString(){
    return "Patient{" + "id=" + id + ", version=" + version + ", healthId='" + healthId + '\'' + ", name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", gender=" + gender + ", bloodType=" + bloodType + ", contactNumber='" + contactNumber + '\'' + ", address=" + address + ", dead=" + dead + ", deathDate=" + deathDate + ", maritalStatus=" + maritalStatus + ", occupation=" + occupation + ", educationLevel=" + educationLevel + ", ageEstimated=" + ageEstimated + ", birthdateEstimated=" + birthdateEstimated + '}';
}


public boolean getBirthdateEstimated(){
    return birthdateEstimated;
}


public void setAge(Integer age){
    this.age = age;
}


}