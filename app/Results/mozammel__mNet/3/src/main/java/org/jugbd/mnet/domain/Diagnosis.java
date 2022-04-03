package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import javax.validation.constraints.Size;
@Entity
public class Diagnosis extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
 private  DiagnosisData burns;

@Size(max = 1000)
 private  String congenitalAnomaly;

@Size(max = 1000)
 private  String neoplastic;

@Size(max = 1000)
 private  String postInfective;

@Size(max = 1000)
 private  String traumatic;

@Size(max = 1000)
 private  String aesthetic;

@Size(max = 1000)
 private  String comment;

@Size(max = 100)
 private  String icd10;

@JsonIgnore
@OneToOne(mappedBy = "diagnosis")
 private  Register register;

@JsonIgnore
@OneToOne(mappedBy = "diagnosis")
 private  OutdoorRegister outdoorRegister;


public Long getVersion(){
    return version;
}


public String getCongenitalAnomaly(){
    return congenitalAnomaly;
}


public String getIcd10(){
    return icd10;
}


public void setCongenitalAnomaly(String congenitalAnomaly){
    this.congenitalAnomaly = congenitalAnomaly;
}


public void setNeoplastic(String neoplastic){
    this.neoplastic = neoplastic;
}


public void setVersion(Long version){
    this.version = version;
}


public void setBurns(DiagnosisData burns){
    this.burns = burns;
}


public void setIcd10(String icd10){
    this.icd10 = icd10;
}


public void setAesthetic(String aesthetic){
    this.aesthetic = aesthetic;
}


public Long getId(){
    return id;
}


public Diagnosis setRegister(Register register){
    this.register = register;
    return this;
}


public String getAesthetic(){
    return aesthetic;
}


public String getPostInfective(){
    return postInfective;
}


public Register getRegister(){
    return register;
}


public Diagnosis setOutdoorRegister(OutdoorRegister outdoorRegister){
    this.outdoorRegister = outdoorRegister;
    return this;
}


public String getTraumatic(){
    return traumatic;
}


public DiagnosisData getBurns(){
    return burns;
}


public void setPostInfective(String postInfective){
    this.postInfective = postInfective;
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


public void setTraumatic(String traumatic){
    this.traumatic = traumatic;
}


public String getNeoplastic(){
    return neoplastic;
}


public OutdoorRegister getOutdoorRegister(){
    return outdoorRegister;
}


}