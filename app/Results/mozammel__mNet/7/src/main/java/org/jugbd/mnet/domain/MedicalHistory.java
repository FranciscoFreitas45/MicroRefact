package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.MenstrualCycle;
import org.jugbd.mnet.domain.enums.PastMedicalHistory;
import javax.persistence;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
@Entity
public class MedicalHistory extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@Column(length = 6)
@Enumerated(EnumType.STRING)
 private  PastMedicalHistory pastMedicalHistory;

@Column(nullable = true)
 private  Integer period;

@Column(nullable = true)
 private  Integer days;

@Column(length = 10)
@Enumerated(EnumType.STRING)
 private  MenstrualCycle menstrualCycle;

@Size(max = 1000)
 private  String pastSurgicalHistory;

@Size(max = 1000)
 private  String drugHistory;

@Size(max = 1000)
 private  String familyHistory;

 private  Boolean similarDiseasesInFamily;

@Size(max = 1000)
 private  String presentIllness;

@Size(max = 1000)
 private  String comments;

@Transient
 private  Register register;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public void setPresentIllness(String presentIllness){
    this.presentIllness = presentIllness;
}


public Long getId(){
    return id;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public void setDrugHistory(String drugHistory){
    this.drugHistory = drugHistory;
}


public Integer getPeriod(){
    return period;
}


public PastMedicalHistory getPastMedicalHistory(){
    return pastMedicalHistory;
}


public void setDays(Integer days){
    this.days = days;
}


public MenstrualCycle getMenstrualCycle(){
    return menstrualCycle;
}


public String getComments(){
    return comments;
}


public void setId(Long id){
    this.id = id;
}


public void setMenstrualCycle(MenstrualCycle menstrualCycle){
    this.menstrualCycle = menstrualCycle;
}


public void setComments(String comments){
    this.comments = comments;
}


public void setSimilarDiseasesInFamily(Boolean similarDiseasesInFamily){
    this.similarDiseasesInFamily = similarDiseasesInFamily;
}


public Long getVersion(){
    return version;
}


public void setVersion(Long version){
    this.version = version;
}


public String getPastSurgicalHistory(){
    return pastSurgicalHistory;
}


public String getDrugHistory(){
    return drugHistory;
}


public void setPastMedicalHistory(PastMedicalHistory pastMedicalHistory){
    this.pastMedicalHistory = pastMedicalHistory;
}


public void setPeriod(Integer period){
    this.period = period;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public void setFamilyHistory(String familyHistory){
    this.familyHistory = familyHistory;
}


public Boolean getSimilarDiseasesInFamily(){
    return similarDiseasesInFamily;
}


public Integer getDays(){
    return days;
}


public String getFamilyHistory(){
    return familyHistory;
}


public String getPresentIllness(){
    return presentIllness;
}


@Override
public String toString(){
    return "MedicalHistory{" + "id=" + id + ", version=" + version + ", pastMedicalHistory=" + pastMedicalHistory + ", period=" + period + ", days=" + days + ", menstrualCycle=" + menstrualCycle + ", pastSurgicalHistory='" + pastSurgicalHistory + '\'' + ", drugHistory='" + drugHistory + '\'' + ", familyHistory='" + familyHistory + '\'' + ", similarDiseasesInFamily=" + similarDiseasesInFamily + '}';
}


public void setPastSurgicalHistory(String pastSurgicalHistory){
    this.pastSurgicalHistory = pastSurgicalHistory;
}


}