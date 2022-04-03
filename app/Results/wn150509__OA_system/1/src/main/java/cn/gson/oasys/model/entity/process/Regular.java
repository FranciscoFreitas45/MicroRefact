package cn.gson.oasys.model.entity.process;
 import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Table
@Entity(name = "aoa_regular")
public class Regular {

@Id
@Column(name = "regular_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long regularId;

 private  String experience;

 private  String understand;

 private  String pullulate;

 private  String deficiency;

 private  String dobetter;

 private  String advice;

 private  Double days;

@Column(name = "personnel_advice")
 private  String personnelAdvice;

@Column(name = "manager_advice")
 private  String managerAdvice;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pro_id")
 private  ProcessList proId;

@Transient
 private  String nameuser;


public String getDeficiency(){
    return deficiency;
}


public String getPersonnelAdvice(){
    return personnelAdvice;
}


public Long getRegularId(){
    return regularId;
}


public String getExperience(){
    return experience;
}


public void setNameuser(String nameuser){
    this.nameuser = nameuser;
}


public ProcessList getProId(){
    return proId;
}


public void setDays(Double days){
    this.days = days;
}


public String getNameuser(){
    return nameuser;
}


public String getManagerAdvice(){
    return managerAdvice;
}


public String getDobetter(){
    return dobetter;
}


public String getPullulate(){
    return pullulate;
}


public void setPullulate(String pullulate){
    this.pullulate = pullulate;
}


public void setDobetter(String dobetter){
    this.dobetter = dobetter;
}


public String getUnderstand(){
    return understand;
}


public void setExperience(String experience){
    this.experience = experience;
}


public void setDeficiency(String deficiency){
    this.deficiency = deficiency;
}


public void setRegularId(Long regularId){
    this.regularId = regularId;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public void setPersonnelAdvice(String personnelAdvice){
    this.personnelAdvice = personnelAdvice;
}


public void setAdvice(String advice){
    this.advice = advice;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public Double getDays(){
    return days;
}


@Override
public String toString(){
    return "Regular [regularId=" + regularId + ", experience=" + experience + ", understand=" + understand + ", pullulate=" + pullulate + ", deficiency=" + deficiency + ", dobetter=" + dobetter + ", advice=" + advice + ", days=" + days + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser + "]";
}


public void setUnderstand(String understand){
    this.understand = understand;
}


public String getAdvice(){
    return advice;
}


}