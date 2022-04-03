package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "project_experience")
public class ProjectExperience {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String projectName;

 private  String projectLevel;

 private  String fromTime;

 private  String toTime;

 private  String projectDescription;

 private  String jobDescription;

public ProjectExperience() {
}public ProjectExperience(String phone, String projectName, String projectLevel, String fromTime, String toTime, String projectDescription, String jobDescription) {
    this.phone = phone;
    this.projectName = projectName;
    this.projectLevel = projectLevel;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.projectDescription = projectDescription;
    this.jobDescription = jobDescription;
}
public String getPhone(){
    return phone;
}


public String getProjectName(){
    return projectName;
}


public String getProjectLevel(){
    return projectLevel;
}


public void setProjectLevel(String projectLevel){
    this.projectLevel = projectLevel;
}


public void setProjectDescription(String projectDescription){
    this.projectDescription = projectDescription;
}


public String getFromTime(){
    return fromTime;
}


public void setToTime(String toTime){
    this.toTime = toTime;
}


public String getJobDescription(){
    return jobDescription;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getProjectDescription(){
    return projectDescription;
}


public void setProjectName(String projectName){
    this.projectName = projectName;
}


public void setJobDescription(String jobDescription){
    this.jobDescription = jobDescription;
}


public String getToTime(){
    return toTime;
}


public void setId(Integer id){
    this.id = id;
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


}