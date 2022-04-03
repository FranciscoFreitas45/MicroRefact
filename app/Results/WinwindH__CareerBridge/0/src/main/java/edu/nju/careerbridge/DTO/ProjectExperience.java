package edu.nju.careerbridge.DTO;
 import javax.persistence;
public class ProjectExperience {

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


public void setProjectDescription(String projectDescription){
    this.projectDescription = projectDescription;
}


public String getFromTime(){
    return fromTime;
}


public String getJobDescription(){
    return jobDescription;
}


public Integer getId(){
    return id;
}


public String getProjectDescription(){
    return projectDescription;
}


public void setJobDescription(String jobDescription){
    this.jobDescription = jobDescription;
}


public String getToTime(){
    return toTime;
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


}