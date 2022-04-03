package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "job_experience")
public class JobExperience {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String companyName;

 private  String companyQuality;

 private  String companyLevel;

 private  String job;

 private  String fromTime;

 private  String toTime;

 private  String description;

public JobExperience() {
}public JobExperience(String phone, String companyName, String companyQuality, String companyLevel, String job, String fromTime, String toTime, String description) {
    this.phone = phone;
    this.companyName = companyName;
    this.companyQuality = companyQuality;
    this.companyLevel = companyLevel;
    this.job = job;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.description = description;
}
public String getPhone(){
    return phone;
}


public String getCompanyLevel(){
    return companyLevel;
}


public void setCompanyQuality(String companyQuality){
    this.companyQuality = companyQuality;
}


public String getFromTime(){
    return fromTime;
}


public void setToTime(String toTime){
    this.toTime = toTime;
}


public String getCompanyQuality(){
    return companyQuality;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setDescription(String description){
    this.description = description;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getDescription(){
    return description;
}


public void setCompanyLevel(String companyLevel){
    this.companyLevel = companyLevel;
}


public String getJob(){
    return job;
}


public String getToTime(){
    return toTime;
}


public String getCompanyName(){
    return companyName;
}


public void setId(Integer id){
    this.id = id;
}


public void setJob(String job){
    this.job = job;
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


}